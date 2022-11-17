package org.kzm.music.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class ConnectUtil {

    public static void main(String[] args) throws IOException {
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
    
    
    
}
