package org.kzm.music.jfoenix;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.flac.FlacInfoReader;
import org.jaudiotagger.audio.flac.FlacStreamReader;
import org.jaudiotagger.audio.generic.GenericAudioHeader;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v1Tag;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class JaudioTaggerTest {

    public static void main(String[] args) throws IOException, CannotReadException, InvalidAudioFrameException, TagException, ReadOnlyFileException {
 /*       FlacInfoReader flacStreamReader=new FlacInfoReader();
        GenericAudioHeader rw = flacStreamReader.read(new RandomAccessFile("D:\\CloudMusic\\苏打绿 - 无与伦比的美丽.flac", "rw"));
        System.out.println(rw);*/
        
        MP3File mp3File=new MP3File("D:\\CloudMusic\\");

        MP3AudioHeader mp3AudioHeader = mp3File.getMP3AudioHeader();

        AbstractID3v2Tag tag = mp3File.getID3v2Tag();
        String name = tag.getFirst(FieldKey.TITLE);
        String author = tag.getFirst(FieldKey.ARTIST);
        String album = tag.getFirst(FieldKey.ALBUM);
        System.out.println();
        
        
    }
}
