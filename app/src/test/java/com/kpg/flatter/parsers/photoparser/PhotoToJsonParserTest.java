package com.kpg.flatter.parsers.photoparser;

import com.google.common.eventbus.EventBus;
import com.kpg.flatter.requests.ApiClient;
import com.kpg.flatter.requests.callbacks.AddPhotoCallback;
import com.kpg.flatter.utills.Urls;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertNotEquals;

public class PhotoToJsonParserTest {

    private static PhotoToJsonParser photoToJsonParser = null;
    private static AddPhotoCallback addPhotoCallback = null;
    private static ApiClient apiClient = null;
    private static PhotoModel photoModel = null;

    @BeforeClass
    public static void setUp() throws Exception {

        photoToJsonParser = new PhotoToJsonParser();
        addPhotoCallback = new AddPhotoCallback(new EventBus());
        photoModel = new PhotoModel();
        photoModel.setUser("tomeczek");
        photoModel.setAlbum("randomalbum");
        photoModel.setTitle("tescik");
        apiClient = new ApiClient(Urls.SERVER.url);
    }

    @Test
    public void addPath() throws FileNotFoundException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("tomeczek.jpg");
        File file = new File(resource.getPath());

        photoToJsonParser.addStream(new FileInputStream(file),photoModel);
        assertNotEquals(0,photoToJsonParser.getPaths().size());
    }

    @Test
    public void parse() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("tomeczek.jpg");
            File file = new File(resource.getPath());
            photoToJsonParser.addStream(new FileInputStream(file),photoModel);
            photoToJsonParser.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseFile() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("tomeczek.jpg");
            File file = new File(resource.getPath());
            photoToJsonParser.addFile(file,photoModel);
            photoToJsonParser.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseString() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("tomeczek.jpg");

            photoToJsonParser.addPath(resource.getPath(),photoModel);
            photoToJsonParser.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseURL() {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource("tomeczek.jpg");
            photoToJsonParser.addURl(resource,photoModel);
            photoToJsonParser.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getJsonObjects() {
        assertNotEquals(0,photoToJsonParser.getJsonObjects().size());
    }
}