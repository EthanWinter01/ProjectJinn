package scenes;

import java.util.ArrayList;
import java.util.List;
import base.AudibleObject;
import base.BackgroundAudio;
import base.BaseObject;
import base.BaseScene;
import base.Blinker;
import base.FadeEffect;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.Background;
import javafx.util.Duration;

public class Scene6 extends BaseScene {
	// Fields
	private int sceneGuider = 0;
	private static final String BG_PATH = "scene6/BG_scene6_1.png";
	private static final String BG_AUDIO_PATH = "scene6/sound/french-countryside-sunrise-17100.mp3";
	private ArrayList<Background> backgroundList;
	private BaseObject eye, alert1, alert2, attend, gradeletter, group_mem, halfeye, head_left, head_mid, head_right, left, mid, right, letterA, showrank, submission;
	
	// Constructor
	public Scene6() {
		super(BG_PATH);
		BackgroundAudio.stopAudio();
		BackgroundAudio.playAudio(BG_AUDIO_PATH);
		initializeBackgrounds();
		initializeObjects();
		setupEventHandlers();
		
		this.getChildren().addAll(eye, alert1, alert2, attend, gradeletter, group_mem, halfeye, head_left,
				head_mid, head_right, left, mid, right, letterA, showrank, submission, new Blinker(), new FadeEffect(3));
	}

	private void initializeBackgrounds() {
		backgroundList = new ArrayList<Background>();
		String[] bg = {"end_credit", "BG_scene_Zone"};
		for (String file: bg) {
			backgroundList.add(createBackground("scene6/" + file + ".png"));
		}
		for (int i=2; i<10; i++) {
			backgroundList.add(createBackground("scene6/BG_scene6_" + i + ".png"));
		}
	}
	
	@Override
	protected void initializeObjects() {
		eye = createBaseObject("eye");
		alert1 = createBaseObject("alert1");
		alert2 = createBaseObject("alert2");
		gradeletter = createBaseObject("gradeletter");
		halfeye = createBaseObject("halfeye");
		head_left = createBaseObject("head_left");
		head_mid = createBaseObject("head_mid");
		head_right = createBaseObject("head_right");
		left = createBaseObject("left");
		mid = createBaseObject("mid");
		right = createBaseObject("right");
		letterA = createBaseObject("letterA");
		attend = createBaseObject("attend");
		group_mem = createBaseObject("group_mem");
		showrank = createBaseObject("showrank");
		submission = createBaseObject("submission");
		
		hideObjects();
	}

	@Override
	protected void setupEventHandlers() {
		handleThisSceneClick(0);
		eye.onClick(() -> handleEyeClick(1));
		alert1.onClick(() -> handelAlertClick(2));
		halfeye.onClick(() -> handleHalfEyeClick(3));
		gradeletter.onClick(() -> handleGradeLetterClick(4));
		letterA.onClick(() -> handleLetterAClick(5));
		left.onClick(() -> closeAndOpen(left, head_left, 6, 8));
		mid.onClick(() -> closeAndOpen(mid, head_mid, 6, 8));
		right.onClick(() -> closeAndOpen(right, head_right, 6, 8));
		head_left.onClick(() -> handleHeadClick(9));
		head_mid.onClick(() -> handleHeadClick(9));
		head_right.onClick(() -> handleHeadClick(9));
	}
	
    private BaseObject createBaseObject(String name) {
        return new BaseObject("scene6/object/" + name + ".png");
    }
	
	private void hideObjects() {
		eye.close();
		alert1.close();
		alert2.close();
		gradeletter.close();
		halfeye.close();
		head_left.close();
		head_mid.close();
		head_right.close();
		left.close();
		mid.close();
		right.close();
		letterA.close();
		
		attend.close();
		group_mem.close();
		showrank.close();
		submission.close();
	}

	private void handleThisSceneClick(int expect) {
		this.setOnMouseClicked(event -> {
			if (sceneGuider == expect) {
				this.setBackground(backgroundList.get(2));
				eye.clickOnly();
				this.setOnMouseClicked(null);
				sceneGuider++;
			}			
		});
	}

	private void handleEyeClick(int expect) {
		if (sceneGuider == expect) {
			BackgroundAudio.stopAudio();
			this.getHeartBeatPlayer().setVolume(0.5);
			this.getHeartBeatPlayer().play();
			eye.close();
			alert1.open();
			sceneGuider++;
		}
	}

	private void handelAlertClick(int expect) {
		if (sceneGuider == expect) {
			this.setBackground(backgroundList.get(3));
			alert1.close();
			halfeye.clickOnly();
			sceneGuider++;
		}
	}

	private void handleHalfEyeClick(int expect) {
		if (sceneGuider == expect) {
			this.getHeartBeatPlayer().setVolume(0.8);
			halfeye.close();
			alert2.viewOnly();
			gradeletter.clickOnly();
			sceneGuider++;
		}
	}
	
	private void handleGradeLetterClick(int expect) {
		if (sceneGuider == expect) {
			gradeletter.close();
			
			letterA.clickOnly();
			this.setBackground(backgroundList.get(4));
			sceneGuider++;
		}
	}

	private void handleLetterAClick(int expect) {
		if (sceneGuider == expect) {
			this.getHeartBeatPlayer().setVolume(1.0);
			letterA.close();
			alert2.close();
			screenBlink();
			sceneGuider++;
		}
	}
	
	private void closeAndOpen(BaseObject toClose, BaseObject toOpen, int begin, int end) {
		if (sceneGuider >= begin && sceneGuider <= end) {
			toClose.close();
			toOpen.open();
			sceneGuider++;
		}
	}
	
	private void handleHeadClick(int expect) {
		if (sceneGuider == expect) {
			head_left.close();
			head_mid.close();
			head_right.close();
			gradeBlink();
			sceneGuider++;
		}
	}
	
	private void screenBlink() {
		glitchSoundMaker();
		sceneBlinkGeneral(5, List.of(5, 6), new int[]{100, 100}, () -> { 
	        this.setBackground(backgroundList.get(1));
			left.open();
			mid.open();
	        right.open();
		});
	}
	
	private void gradeBlink() {
		glitchSoundMaker();
		sceneBlinkGeneral(5, List.of(7, 8, 9), new int[]{50, 50, 100}, () -> {
			this.getChildren().add(new FadeEffect(2));
			this.setBackground(backgroundList.get(0));
			BackgroundAudio.playAudio(BG_AUDIO_PATH);
		});
	}
	
	private void sceneBlinkGeneral(int durationSeconds, List<Integer> backgroundIndices, int[] sleepDurations, Runnable finalAction) {
		Thread blinkThread = new Thread(() -> {
	        long endTime = System.currentTimeMillis() + durationSeconds * 1000L;
	        try {
	            while (System.currentTimeMillis() < endTime) {
	                for (int i = 0; i < backgroundIndices.size(); i++) {
	                    int index = backgroundIndices.get(i);
	                    Platform.runLater(() -> setBackground(backgroundList.get(index)));

	                    if (i < sleepDurations.length) {
	                        Thread.sleep(sleepDurations[i]);
	                    }
	                }
	            }
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        } finally {
	            Platform.runLater(finalAction);
	        }
	    });

	    blinkThread.setDaemon(true);
	    blinkThread.start();
	}
	
	private void glitchSoundMaker() {
		AudibleObject glitch = new AudibleObject("", "scene5/sound/electric_shock.mp3");
		glitch.playAudio();
		Timeline stopTimeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
            glitch.getMediaPlayer().stop();
        }));
		stopTimeline.play();
	}
}
