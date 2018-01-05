package Utils;

import java.util.ArrayList;

public class SoundUtil {
    private static final ArrayList<String> voices = new ArrayList<>();

    public static void createWavFiles(ArrayList<String> tags) {
        //init the voices
        initVoices();

        TextToSpeech tts = new TextToSpeech();
        for (int i = 0; i < tags.size(); i++) {
            tts.saveToFile(tags.get(i), 1.0f, false, false, tags.get(i));
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
