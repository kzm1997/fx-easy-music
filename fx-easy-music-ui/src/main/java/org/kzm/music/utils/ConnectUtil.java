package org.kzm.music.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.kzm.music.pojo.SongAlbum;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.ExecutionException;

public class ConnectUtil {


    private static final Map<String, String> HEADERS = new HashMap<>();

    static {
        HEADERS.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        HEADERS.put("Referer", "https//music.163.com");
        HEADERS.put("Host", "music.163.com");
    }

    /**
     * 获取一页网易云歌单
     *
     * @return
     */
    public static List<SongAlbum> getSongList(String cat) {
        if ("全部歌单".equals(cat)) {
            cat = "全部";
        }
        try {
            cat = URLEncoder.encode(cat, String.valueOf(StandardCharsets.UTF_8));
            int offset = (int) (Math.random() * 37.0D + 1.0D) * 35;
            String url = "https://music.163.com/discover/playlist/?order=hot&cat=" + cat + "&limit=35&offset=" + offset;
            url="https://music.163.com/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset=455";
            System.out.println(url);
            String html = getHtml(url);
            
            if (!"error".equals(html)){
                Document document = Jsoup.parse(html);
                Elements listItems = document.getElementsByClass("u-cover u-cover-1");
                Elements aItems = listItems.select("a");
                Elements imageItems=listItems.select("img");
                
                List<SongAlbum> songAlbumList=new ArrayList<>();
                
                //获得超链接标签第一个为歌单url,第二个为播放图标url,因此设置步长为2
                for (int i=0;i<aItems.size();i+=2){
                    Element a=aItems.get(i);
                    String title = a.attr("title"); 
                    String herf = a.attr("href"); //歌单id
                    String picUrl = imageItems.get(i / 2).attr("src");
                    String playListUrl = "https://music.163.com" + herf;
                    songAlbumList.add(new SongAlbum(title, picUrl, playListUrl, getSongListInfo(playListUrl)));
                }
                return songAlbumList;
            }
            
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
        
    }


    /**
     * 获取歌单简介
     * @param url
     * @return
     */
    private static String getSongListInfo(String url){
        String html=getHtml(url.replace("#/",""));
        Document document = Jsoup.parse(html);
        Elements pHideItems = document.getElementsByClass("intr f-brk f-hide");
        Elements pItems = document.getElementsByClass("intr f-brk");
        
        String info;
        if (pHideItems.text().length()==0){
            info=pItems.text();
        }else{
            info=pHideItems.text();
        }

        return info;
    }


    /**
     * 获取网页dom
     * @param url
     * @return
     */
    public static String getHtml(String url) {
        try {
            Connection connection = Jsoup.connect(url).ignoreContentType(true);
            for (String header: HEADERS.keySet()) {
                connection.header(header,HEADERS.get(header));
            }
            connection.method(Connection.Method.GET);
            connection.timeout(1000);
            return connection.execute().body();
        } catch (IOException e) {
            return "error";
        }
        
    }
    
    public static void  downloadSong(){
        //System.out.println(HttpClient.get("http://music.163.com/api/song/detail/?id=" + 1935811897 + "&ids=%5B+" + 1935811897 + "%5D"));
        //System.out.println(HttpClient.get("https://music.163.com/#/song?id=" + 1935811897));

        Scanner sc = new Scanner(System.in);
        System.out.print("请选择下载类型（1.歌单id 2.歌曲id）：");
        String temp = sc.nextLine();
        List<Map<String, Object>> playlist = new ArrayList<Map<String, Object>>();
        String ids;
        if ("1".equals(temp)) {
            System.out.print("请输入歌单id（仅限单id）：");
            String playId = sc.nextLine();
            ids = API.playlist_detail(playId, playlist);
        } else if ("2".equals(temp)) {
            System.out.print("请输入歌曲id（支持多个id用','隔开）：");
            String songIds = sc.nextLine();
            ids = API.song_detail(songIds, playlist);
        } else {
            System.out.println("请输入正确类型");
            return;
        }
        playlist = API.song_url(ids, playlist);
        System.out.println("解析完成，共下载" + playlist.size() + "首歌曲");
        if (!Util.createDir("music163")) {
            System.out.println("创建文件夹失败，退出下载");
            return;
        }
        for (Map<String, Object> map : playlist) {
            if (map.get("url") != null) {
                try {
                    byte[] data = Util.fileDownload(map.get("url").toString());
                    FileOutputStream outputStream = new FileOutputStream(new File("music163" + File.separator + Util.formatFilePath(map.get("name").toString()) + ".mp3"));
                    outputStream.write(data);
                    System.out.println("已下载歌曲：" + Util.formatFilePath(map.get("name").toString()));
                    outputStream.close();
                } catch (IOException e) {
                    System.out.println("无法创建歌曲文件：" + map.get("name"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("无法获取下载地址：" + map.get("name"));
            }
        }
        System.exit(0);
    }


    public static void main(String[] args) throws IOException {
        List<SongAlbum> songList = getSongList("全部歌单");
        System.out.println(songList.size());
    }


}
