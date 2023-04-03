package org.kzm.music.utils;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LocalMusicUtils {

    /**
     * 获取本地音乐的歌词
     *
     * @param lrcPath 本地音乐lrc文件的路径
     * @return 返回本地音乐歌词，以字符串表示
     */
    public static String getLrc(String lrcPath){
        String lrc = null;
        File lrcFile = null;
        try {
            lrcFile = new File(new URI(lrcPath));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        if (!lrcFile.exists()) {
            lrc = "[00:00.00]无歌词";
        } else {
            try {
                byte[] fileBytes = Files.readAllBytes(Paths.get(lrcFile.toURI()));
                lrc = new String(fileBytes, StandardCharsets.UTF_8);
            } catch (Exception e) {
                lrc = "[00:00.00]无歌词";
            }
        }
        return lrc;
    }
}
