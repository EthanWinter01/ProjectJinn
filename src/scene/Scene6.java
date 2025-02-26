package scene;

import component.ImageObject;
import javafx.application.Platform;
import javafx.scene.layout.Background;
/*
Scene 6
หน้า BG_scene6_1
	กด jor แล้วพาเข้า BG_scene6_2
หน้า BG_scene6_2
	กดeye แล้ว alert 1 โผล่
	กดdown แล้วเข้า หน้า BG_scene6_3
หน้า BG_scene6_3
	กดhalfeye แล้ว alert2 โผล่
	ถ้ากด gradeletter จะพาเข้า BG_scene6_4
หน้า BG_scene6_4
	ถ้ากด letterA จะพาเข้าแฟลชที่เกิดจาก BG_scene6_5 + BG_scene6_6
	หยุดแฟลชแล้วจะไปเข้าหน้าBG_scene_zone
หน้า BG_scene_zone
	ถ้ากดleft จะมี head_leftขึ้น
	ถ้ากดmid จะมี head_midขึ้น
	ถ้ากด right จะมี head_rightขึ้น
	เมื่อครบทุบ head ถ้ากดheadใดheadหนึ่งก็จะตัดเข้า แฟลช
	แฟลชเกิดจาก BG_scene6_7 + BG_scene6_8 + BG_scene6_9
	จบแฟลชให้ตัดเข้าหน้า end_credit
	
*/
public class Scene6 extends ScenePane {

	private Background[] backgrounds;
	private ImageObject  eye, alert1, alert2, attend, gradeletter, group_mem, halfeye, head_left, head_mid, head_right, left, mid, right, letterA, showrank, submission;
	private int next = 2;
	
	public Scene6() {
		super("scene6/BG_scene6_1.png");
		backgrounds = new Background[10];
		backgrounds[0] = createBackground("scene6/end_credit.png");
		backgrounds[1] = createBackground("scene6/BG_scene_Zone.png");
		
		for (int i=2; i<10; i++) {
			backgrounds[i] = createBackground("scene6/BG_scene6_" + i + ".png");
		}
		
		eye = new ImageObject("scene6/object/eye.png");
		alert1 = new ImageObject("scene6/object/alert1.png");
		alert2 = new ImageObject("scene6/object/alert2.png");
		attend = new ImageObject("scene6/object/attend.png");
		gradeletter = new ImageObject("scene6/object/gradeletter.png");
		group_mem = new ImageObject("scene6/object/group_mem.png");
		halfeye = new ImageObject("scene6/object/halfeye.png");
		head_left = new ImageObject("scene6/object/head_left.png");
		head_mid = new ImageObject("scene6/object/head_mid.png");
		head_right = new ImageObject("scene6/object/head_right.png");
		left = new ImageObject("scene6/object/left.png");
		mid = new ImageObject("scene6/object/mid.png");
		right = new ImageObject("scene6/object/right.png");
		letterA = new ImageObject("scene6/object/letterA.png");
		showrank = new ImageObject("scene6/object/showrank.png");
		submission = new ImageObject("scene6/object/submission.png");
		
		
		this.setOnMouseClicked(event -> { // this should activate by jor?
				this.setBackground(backgrounds[2]);
				this.getChildren().add(eye);
				this.setOnMouseClicked(null);
		});
		
		eye.setOnMouseClicked(event -> {
			this.getChildren().remove(eye);
			this.getChildren().add(alert1);
			// where is down?
		});
		
		alert1.setOnMouseClicked(event -> {
			this.getChildren().remove(alert1);
			this.setBackground(backgrounds[3]);
			this.getChildren().add(halfeye);
		});
		
		halfeye.setOnMouseClicked(event -> {
			this.getChildren().remove(halfeye);
			this.getChildren().addAll(alert2, gradeletter);
		});
		
		gradeletter.setOnMouseClicked(event -> {
			this.getChildren().remove(gradeletter);
			this.setBackground(backgrounds[4]);
			this.getChildren().add(letterA);
		});
		
		letterA.setOnMouseClicked(event -> {
			this.getChildren().clear();
			sceneBlink(5);
		});
		
		left.setOnMouseClicked(event -> {
			this.getChildren().remove(left);
			this.getChildren().add(head_left);
		});
		
		mid.setOnMouseClicked(event -> {
			this.getChildren().remove(mid);
			this.getChildren().add(head_mid);
		});
		
		right.setOnMouseClicked(event -> {
			this.getChildren().remove(right);
			this.getChildren().add(head_right);
		});
	}
	
    private void sceneBlink(int durationSeconds) {
        Thread blinkThread = new Thread(() -> {
            long endTime = System.currentTimeMillis() + durationSeconds * 1000L;

            try {
                while (System.currentTimeMillis() < endTime) {
                    Thread.sleep(100);
                    Platform.runLater(() -> {
                        setBackground(backgrounds[5]);
                    });

                    Thread.sleep(100);
                    Platform.runLater(() -> {
                        setBackground(backgrounds[6]);
                    });
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {

                Platform.runLater(() -> {
                    setBackground(backgrounds[1]);
                    this.getChildren().addAll(left, right, mid);
                });
            }
        });

        blinkThread.setDaemon(true);
        blinkThread.start();
    }

}
