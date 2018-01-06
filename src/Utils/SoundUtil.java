package Utils;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SoundUtil {
    private static final ArrayList<String> voices = new ArrayList<>();

    public static void createWavFiles(ArrayList<String> tags) {
        //clear audio directory
        try {
            FileUtils.deleteDirectory(new File("audio"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //init the voices
        initVoices();

        TextToSpeech tts = new TextToSpeech();
        for (int i = 0; i < tags.size(); i++) {
            String fileName = tags.get(i);
            //replace the '.' with blank space
            fileName = fileName.replaceAll("[^A-Za-z0-9 ]","");

            tts.setVoice(voices.get(i%5));
            tts.saveToFile(tags.get(i), 1.0f, false, false, fileName);
        }
    }

    private static void initVoices() {
        voices.add("cmu-slt-hsmm");
        voices.add("dfki-obadiah-hsmm");
        voices.add("dfki-spike-hsmm");
        voices.add("cmu-bdl-hsmm");
        voices.add("cmu-rms-hsmm");
        voices.add("dfki-poppy-hsmm");
    }
}
