package de.fade.hideAndSeek.Utils;

import de.fade.hideAndSeek.HideAndSeek;
import org.bukkit.Bukkit;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Configfiles {

    private String name;
    private File file;
    private Yaml yaml;

    public Configfiles(String name) {
        this.name = name;
        this.file = new File(HideAndSeek.getInstance().getDataFolder(), name);
    }


    public File getFile() {
        return this.file;
    }

    public void writeFile(String categorie,String key, String content) throws IOException {
        // Map für die Lobby
        Map<String, Object> map = new HashMap<>();
        map.put(key, content);

        // Obermappe "Spawn"
        Map<String, Object> data = new HashMap<>();
        data.put(categorie, map);

        this.yaml = new Yaml();
        try (FileWriter writer = new FileWriter(this.file)) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String readFile(String categorie,String key) throws IOException {
        FileInputStream inputStream = new FileInputStream(this.file);
        Map<String, Object> data = yaml.load(inputStream);
        // Zugriff auf "Spawn" → "Lobby"
        Map<String, Object> catMap = (Map<String, Object>) data.get(categorie);
        String keyValues = (String) catMap.get(key);
        return keyValues;
    }

    public String[] readFile(String categorie,String key, String splitby) throws IOException {
        FileInputStream inputStream = new FileInputStream(this.file);
        Map<String, Object> data = yaml.load(inputStream);
        // Zugriff auf "Spawn" → "Lobby"
        Map<String, Object> catMap = (Map<String, Object>) data.get(categorie);
        String keyValues = (String) catMap.get(key);
        String[] parts = keyValues.split(splitby);
        return parts;
    }


}
