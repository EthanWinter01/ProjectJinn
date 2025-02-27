package scene;

import java.util.ArrayList;
import java.util.Arrays;

import component.ImageObject;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.GameLogic;
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
	private int countDown = 3;
	
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
		gradeletter = new ImageObject("scene6/object/gradeletter.png");
		halfeye = new ImageObject("scene6/object/halfeye.png");
		head_left = new ImageObject("scene6/object/head_left.png");
		head_mid = new ImageObject("scene6/object/head_mid.png");
		head_right = new ImageObject("scene6/object/head_right.png");
		left = new ImageObject("scene6/object/left.png");
		mid = new ImageObject("scene6/object/mid.png");
		right = new ImageObject("scene6/object/right.png");
		letterA = new ImageObject("scene6/object/letterA.png");
		
		
		// where to use
		attend = new ImageObject("scene6/object/attend.png");
		group_mem = new ImageObject("scene6/object/group_mem.png");
		showrank = new ImageObject("scene6/object/showrank.png");
		submission = new ImageObject("scene6/object/submission.png");
		
		
		this.getChildren().addAll(eye, alert1, alert2, attend, gradeletter, group_mem, halfeye, head_left, head_mid, head_right, left, mid, right, letterA, showrank, submission);

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
		
		
		// where to use
		attend.close();
		group_mem.close();
		showrank.close();
		submission.close();
		
		this.setOnMouseClicked(event -> { // this should activate by jor?
				this.setBackground(backgrounds[2]);
				eye.open();
				this.setOnMouseClicked(null);
		});
		
		eye.setOnMouseClicked(event -> {
			eye.close();
			alert1.open();
			// where is down?
		});
		
		alert1.setOnMouseClicked(event -> {
			alert1.close();
			this.setBackground(backgrounds[3]);
			halfeye.open();
		});
		
		halfeye.setOnMouseClicked(event -> {
			halfeye.close();
			alert2.open();
			gradeletter.open();
		});
		
		gradeletter.setOnMouseClicked(event -> {
			gradeletter.close();
			this.setBackground(backgrounds[4]);
			letterA.open();
		});
		
		letterA.setOnMouseClicked(event -> {
			letterA.close();
			alert2.close();
			sceneBlink(5);
		});
		
		left.setOnMouseClicked(event -> {
			left.close();
			head_left.open();
			countDown--;
		});
		
		mid.setOnMouseClicked(event -> {
			mid.close();
			head_mid.open();
			countDown--;
		});
		
		right.setOnMouseClicked(event -> {
			right.close();
			head_right.open();
			countDown--;
		});
		
		head_left.setOnMouseClicked(event -> {
			if (countDown == 0) {
				head_left.close();
				head_mid.close();
				head_right.close();
				sceneBlink3(5);
			}
		});
		
		head_mid.setOnMouseClicked(event -> {
			if (countDown == 0) {
				head_left.close();
				head_mid.close();
				head_right.close();
				sceneBlink3(5);
			}	
		});

		head_right.setOnMouseClicked(event -> {
			if (countDown == 0) {
				head_left.close();
				head_mid.close();
				head_right.close();
				sceneBlink3(5);
			}
		});
		
		eye.close();
		
		Rectangle fadeOverlay = new Rectangle(900, 650, Color.BLACK);
		this.getChildren().addAll(fadeOverlay, GameLogic.getBlinker().blackScene);
		FadeTransition sceneFadeIn = new FadeTransition(Duration.seconds(3), fadeOverlay);
        sceneFadeIn.setFromValue(1.0); 
        sceneFadeIn.setToValue(0.0);   
        sceneFadeIn.setDelay(Duration.seconds(2));
        sceneFadeIn.setOnFinished(event -> {
        	this.getChildren().remove(fadeOverlay);
        	eye.setMouseTransparent(false);
        	this.setCursor(Cursor.DEFAULT);
        	
        });
        this.setCursor(Cursor.HAND);
        sceneFadeIn.play(); 
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
                    left.open();
                    mid.open();
                    right.open();
                });
            }
        });

        blinkThread.setDaemon(true);
        blinkThread.start();
    }
    
    private void sceneBlink3(int durationSeconds) {
        Thread blinkThread = new Thread(() -> {
            long endTime = System.currentTimeMillis() + durationSeconds * 1000L;

            try {
                while (System.currentTimeMillis() < endTime) {
                    Thread.sleep(50);
                    Platform.runLater(() -> {
                        setBackground(backgrounds[7]);
                    });

                    Thread.sleep(50);
                    Platform.runLater(() -> {
                        setBackground(backgrounds[8]);
                    });
                    Thread.sleep(100);
                    Platform.runLater(() -> {
                        setBackground(backgrounds[9]);
                    });
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {

                Platform.runLater(() -> {
                    setBackground(backgrounds[0]);
                });
            }
        });

        blinkThread.setDaemon(true);
        blinkThread.start();
    }    

}
