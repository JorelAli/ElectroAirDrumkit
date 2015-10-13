package io.github.skepter.ead;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Utilities {

	public enum SoundType {
		BASS_DRUM_A1("bassdrum_acoustic01"),
		BASS_DRUM_A2("bassdrum_acoustic02"),
		
		BASS_DRUM1("bassdrum01"),
		BASS_DRUM2("bassdrum02"),
		BASS_DRUM3("bassdrum03"),
		BASS_DRUM4("bassdrum04"),
		
		CLAP1("clap01"),
		CLAP2("clap02"),
		CLAP3("clap03"),
		CLAP4("clap04"),
		
		CLAV1("clav01"),
		CLAV2("clav02"),
		
		CRASH1("crash01"),
		CRASH2("crash02"),
		
		HIHAT_CLOSED1("hihat_closed01"),
		HIHAT_CLOSED2("hihat_closed02"),
		HIHAT_CLOSED3("hihat_closed03"),
		HIHAT_CLOSED4("hihat_closed04"),
		HIHAT_CLOSED5("hihat_closed05"),
	
		SNARE1("snare01"),
		SNARE2("snare02"),
		SNARE3("snare03"),
		SNARE4("snare04"),
		SNARE5("snare05"),
		SNARE6("snare06"),
		SNARE7("snare07"),
		
		TOM1("tom01"),
		TOM2("tom02"),
		TOM3("tom03"),
		TOM4("tom04"),
		TOM5("tom05");
		
//		hihat_foot_pedal01.wav
//		hihat_opened01.wav
//		hihat_opened02.wav
//		hihat_opened03.wav
//		kick_distorted01.wav
//		kick_hard01.wav
//		kick_hardcore01.wav
//		kick_hiphop01.wav
//		kick_long01.wav
//		kick_soft01.wav
//		kick_soft02.wav
//		kick01.wav
//		kick02.wav
//		kick03.wav
//		nasty_bass01.wav
//		nasty_rim01.wav
//		nasty_snare01.wav
//		ride01.wav
//		ride02.wav
//		rim01.wav
//		shaker01.wav
//		shaker02.wav
//		shaker03.wav
//		sidestick01.wav
//		snare_acoustic01.wav
//		snare_electro01.wav
//		snare_harsh01.wav
//		snare_hiphop01.wav
//		snare_hiphop02.wav
//		snare_muffled01.wav
//		snare_muffled02.wav
//		snare_rim01.wav
//		snare_short01.wav
//		tom_hi01.wav
//		tom_low01.wav
//		tom_mid01.wav
//		wood01.wav
//		zap01.wav
//		zap02.wav
//		zap03.wav
				
		private String soundName;
		
		SoundType(String str) {
			soundName = str;
		}
	
		public String getSoundName() {
			return soundName;
		}
	} 
	
	public static void playSound(SoundType sound) {
		try {
			System.out.println(sound.getSoundName());
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem.getAudioInputStream(Sample.class.getResourceAsStream("/resources/" + sound.getSoundName() + ".wav"));			
			clip.open(inputStream);
			clip.start();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
