package org.kzm.music.jfoenix;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacInfoReader;
import org.jaudiotagger.audio.flac.FlacStreamReader;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class JaudioTaggerTest {

    public static void main(String[] args) throws IOException, CannotReadException, InvalidAudioFrameException, TagException, ReadOnlyFileException {
        FlacInfoReader flacStreamReader=new FlacInfoReader();
        GenericAudioHeader rw = flacStreamReader.read(new RandomAccessFile("D:\\CloudMusic\\苏打绿 - 无与伦比的美丽.flac", "rw"));
        System.out.println(rw);
        
        MP3File mp3File=new MP3File("D:\\CloudMusic\\逍遥叹-胡歌.mp3");

        MP3AudioHeader mp3AudioHeader = mp3File.getMP3AudioHeader();

        System.out.println(mp3AudioHeader);
        
        
    }
}
