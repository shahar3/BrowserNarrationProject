package audio;

import audio.Source;
import com.gtranslate.utils.WebUtils;
import marytts.client.MaryClient;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Test {

    private static final String[] voices = new String[6];
    private static ArrayList<Source> sourceList;
    private static ArrayList<Integer> bufferList;
    private static int currentPos = 0;

    public static void main(final String[] arg) throws IOException, InterruptedException {
        //init voices
        initVoices();
        //text to speech
        System.out.println("Start");
        AudioMaster.init();
        AudioMaster.setListenerData(0, 0, 0);
        System.out.println("set listener ended");

//
        //get the links
        System.out.println("connect to website");
        audio.WebUtils.connectToWebsite("http://projectaudioforbengurion.000webhostapp.com/");
        ArrayList<String> tags = audio.WebUtils.getItems();
        System.out.println("start create wav files");


        createWavFiles(tags);
        System.out.println("play tags");
        prepareTags(tags);
        playTagsRing(sourceList,bufferList);
//        fib(1,true);
        final int buffer = AudioMaster.loadSound("audio/About.wav");
        final Source source = new Source();
        // fib(4, true);
//
//        final int buffer2 = AudioMaster.loadSound("audio/footsteps.wav");
//        final Source source2 = new Source();
//
//        source2.setLooping(true);
//        source2.play(buffer2);
//
//        source.setLooping(true);
//        source.play(buffer);
////
//        float xPos = 15;
//        source.setPosition(xPos, xPos, 0);
////
////        float xPos2 = -15;
////        source2.setPosition(xPos2,0,2);
////
//        // Added non-blocking input to neatly finish close the app.
//        final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        String c = "";
//        while (!"q".equals(c))
//        {
//            if (bufferedReader.ready())
//            {
//                c = bufferedReader.readLine();
//            }
//            xPos -= 0.03f;
//            if (xPos < -8f)
//            {
//                xPos = 8;
//            }
//            source.setPosition(xPos, xPos, 0);
//            Thread.sleep(10);
//        }
//
//        source.delete();
//
//   AudioMaster.cleanUp();
    }




    private static void playTagsRing(ArrayList<Source> sources, ArrayList<Integer> bufferList) {
        for(int i = currentPos; i < 3; i ++){
            sources.get(i).play(bufferList.get(i));
        }
    }

    private static void initVoices() {
        voices[0] = "cmu-slt-hsmm";
        voices[1] = "dfki-obadiah-hsmm";
        voices[2] = "dfki-spike-hsmm";
        voices[3] = "cmu-bdl-hsmm";
        voices[4] = "cmu-rms-hsmm";
        voices[5] = "dfki-poppy-hsmm";
    }

    private static void fib(int samples, boolean randomize) {
        double rnd = 1;
        double x, y, z, r, pi;

        if (randomize) {
            rnd = Math.random() * samples;
        }
        ArrayList<String> points = new ArrayList<>();
        double offset = 2.0;
        double increment = Math.PI * (3 - Math.sqrt(5));
        for (int i = 0; i < samples; i++) {
            y = (((i * offset) - 1) + (offset / 2)) * 1.5;
            r = 20;
            pi = ((i + rnd) % samples) * increment;
            x = Math.cos(pi) * r;
            z = Math.sin(pi) * r;
            points.add("x : " + x + " y : " + y + " z " + z);
            System.out.println(i + "x : " + x + " y : " + y + " z " + z);
        }
    }


    private static void createWavFiles(ArrayList<String> tags) {
        System.out.println("start speak");
        TextToSpeech tts = new TextToSpeech();
        for (int i = 0; i < 4; i++) {
            tts.setVoice(voices[i]);
            tts.speak(tags.get(i), 1.0f, false, false, tags.get(i));
        }
    }

    private static void prepareTags(ArrayList<String> tags) {

        //        final int buffer = AudioMaster.loadSound("audio/dani.wav");
//        final Source source = new Source();
        int elemntNum = 0;
        bufferList = new ArrayList<Integer>();
        sourceList = new ArrayList<Source>();
        double xInc = 40.000 / 2;
        double yInc = 20.0 / 3.0;
        for (int j = 0; j < tags.size(); j += 3) {
            for (int i = 0; i < 3; i++) {
                final int buffer = AudioMaster.loadSound("audio/" + tags.get(i) + ".wav");
                bufferList.add(buffer);
                final Source source = new Source();
                source.setLooping(true);
                source.setPitch((float) 0.8);
                source.setLooping(true);
                if (i == 2) {
                    source.setPosition((float) (-20 + (i * xInc)), 10, 0);
                }
                source.setPosition((float) (-20 + (i * xInc)), 0, 0);
                System.out.println("x : " + (float) (-20 + (i * xInc)));
                System.out.println("y : " + (float) (0 + (i * yInc)));
                source.setLooping(true);
                sourceList.add(source);
//            source.play(buffer);
                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        AudioMaster.cleanUp();

    }
}