package com.kpg.flatter.parsers.photoparser;

import android.util.Base64;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;


public class PhotoToJsonParser {

    private HashMap<InputStream,PhotoModel> paths;
    private LinkedList<String> jsonObjects;

    public PhotoToJsonParser(HashMap<InputStream,PhotoModel> paths) {
        this.paths = paths;
        this.jsonObjects = new LinkedList<String>();
    }

    public PhotoToJsonParser(){
        this.paths = new HashMap<InputStream,PhotoModel>();
        this.jsonObjects = new LinkedList<String>();
    }

    public void addStream(InputStream inputStream, PhotoModel model){
        this.paths.put(inputStream,model);
    }

    public void addFile(File file, PhotoModel photoModel) throws FileNotFoundException {
        this.paths.put(new FileInputStream(file),photoModel);
    }

    public void addPath(String path,PhotoModel photoModel) throws  FileNotFoundException{
        File file = new File(path);
        this.paths.put(new FileInputStream(file),photoModel);
    }

    public void addURl(URL url, PhotoModel photoModel) throws  FileNotFoundException{
        this.paths.put(new FileInputStream(url.getFile()),photoModel);
    }

    public void parse() throws IOException {

        for (Map.Entry<InputStream,PhotoModel> entry : this.paths.entrySet()){

            InputStream inputStream = entry.getKey();

            byte[] bytes;
            byte[] buffer = new byte[8192];
            int bytesRead;
            ByteArrayOutputStream output = new ByteArrayOutputStream();

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
            bytes = output.toByteArray();
            String encodedString = Base64.encodeToString(bytes, Base64.DEFAULT);

            entry.getValue().setData(encodedString);

            String jsonObject = new Gson().toJson(entry.getValue());
            this.jsonObjects.add(jsonObject);
        }
    }

    public HashMap<InputStream, PhotoModel> getPaths() {
        return paths;
    }

    public LinkedList<String> getJsonObjects() {
        return jsonObjects;
    }

    public void setJsonObjects(LinkedList<String> jsonObjects) {
        this.jsonObjects = jsonObjects;
    }
}
