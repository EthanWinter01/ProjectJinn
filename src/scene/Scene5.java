package scene;

import component.BackgroundMusic;
import component.ImageObject;
import component.NoisyObject;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Cursor;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import logic.GameLogic;

/*
Scene 5
หน้า BG_scene5_1
	กด projector1 แล้ว projector2 โผล่ทับ
	กด monitor แล้วพาเข้าหน้า BG_scene5_2 
หน้า BG_scene5_2
	เกิดแฟลชระหว่าง comsmile_1 comsmile_2 comsmile_3 สักแปปแล้วไปหน้าBG_blackscreen
หน้า BG_blackscreen
	กดblackscreen แล้วมี welcomestupid
	กด welcomestupid แล้วพาเข้า BG_scene5_red
Scene 5
	หน้า BG_scene5_red
	กด timetohavefun แล้วมี tie ทั้ง 7 อันโผล่มาพร้อมกัน
	ถ้า กด tie 1 ->p1โผล่, กด tie 2 ->p2 โผล่, ...., กด tie 7 ->p7 โผล่
	โผล่ครบให้ตัดเข้าซีน6 โดยมีเสียงนาฬิกาปลุกดังเป็นการบอกว่าตื่น	
*/

public class Scene5 extends ScenePane {

	private int sceneGuider = 0;
	private ImageObject[] ties, ps, comsmile, projector;
	private ImageObject blackscreen, keyboard, monitor, mouse, timetohavefun, welcomestupid;
	
	private Background[] backgrounds;

	public Scene5() {
		super("scene5/BG_scene5_1.png");
		BackgroundMusic.stopMusic();
		BackgroundMusic.playMusic("scene5/sound/horrorspeed.mp3");
		
		String[] bg = {"1", "2", "black", "red"};
		backgrounds = new Background[4];
		for (int i=0; i<4; i++) {
			backgrounds[i] = createBackground("scene5/BG_scene5_" + bg[i] + ".png");
		}
		
		ties = new ImageObject[7];
		ps = new ImageObject[7];
		comsmile = new ImageObject[3];
		projector = new ImageObject[2];
		for (int i=0; i<7; i++) {
			ties[i] = new ImageObject("scene5/object/tie" + (i+1) +".png");
			ps[i] = new ImageObject("scene5/object/p" + (i+1) +".png");
			ties[i].close();
			ps[i].close();
		}
		
		comsmile[0] = new ImageObject("scene5/object/comsmile_1.png");
		comsmile[1] = new ImageObject("scene5/object/comsmile_2.png");
		comsmile[2] = new ImageObject("scene5/object/comsmile_3.png");
		projector[0] = new ImageObject("scene5/object/projector1.png");
		projector[1] = new ImageObject("scene5/object/projector2.png");
		blackscreen = new ImageObject("scene5/object/blackscreen.png");
		keyboard = new ImageObject("scene5/object/keyboard.png");
		monitor = new ImageObject("scene5/object/monitor.png");
		mouse = new ImageObject("scene5/object/mouse.png");
		timetohavefun = new ImageObject("scene5/object/timetohavefun.png");
		welcomestupid = new ImageObject("scene5/object/welcomestupid.png");
		
		comsmile[0].close();
		comsmile[1].close();
		comsmile[2].close();
		projector[0].close();
		projector[1].close();
		blackscreen.close();
		keyboard.close();
		monitor.close();
		mouse.close();
		timetohavefun.close();
		welcomestupid.close();
		
		this.getChildren().addAll(
				ties[0], ties[1], ties[2], ties[3], ties[4], ties[5], ties[6],
				ps[0], ps[1], ps[2], ps[3], ps[4], ps[5], ps[6],
				comsmile[0], comsmile[1], comsmile[2], 
				projector[0], projector[1], 
				blackscreen, keyboard, monitor, mouse,
				timetohavefun, welcomestupid
		);
		
		this.setOnMouseClicked(event -> {
		    if (sceneGuider == 0) {
		        projector[0].open();
		        projector[0].setOpacity(0);
		        sceneGuider = 1;
		    } 
		});
		
		projector[0].setOnMouseClicked(event -> {
		    if (sceneGuider == 1) {
		        projector[0].close();
		        projector[1].open();
		        monitor.open();
		        sceneGuider = 2;
		    }
		});
		
		monitor.setOnMouseClicked(event -> {
			if (sceneGuider == 2) {
				projector[1].close();
				monitor.close();
				setBackground(backgrounds[1]);
				runGlitchEffect();
				sceneGuider = 3;
			}
		});
		
		
		blackscreen.setOnMouseClicked(event -> {
		    if (sceneGuider == 4) {
		        blackscreen.close();
		        welcomestupid.open();
		        sceneGuider = 5;
		    }
		});
				
		welcomestupid.setOnMouseClicked(event -> {
		    if (sceneGuider == 5) {
		        welcomestupid.close();
		        timetohavefun.open();
		        
		        Timeline sequence = new Timeline(
		            new KeyFrame(Duration.seconds(1), e -> {
		                setBackground(backgrounds[2]); // Blink black for 0.5 sec
		            }),
		            new KeyFrame(Duration.seconds(1.5), e -> {
		                setBackground(backgrounds[3]); // backgrounds[3] = "scene5_red"
		                BackgroundMusic.stopMusic();
		                BackgroundMusic.playMusic("scene5/sound/pasatV2.mp3");
		                sceneGuider = 6;
		            })
		        );
		        
		        sequence.play();
		    }
		});
		
		timetohavefun.setOnMouseClicked(event -> {
			if (sceneGuider == 6) {
				timetohavefun.close();
				for (int i=0; i<7; i++) {
					ties[i].open();
				}
				sceneGuider = 7;
			}
		});
		
		for (int i=0; i<7; i++) {
			final int x = i;
			ties[i].setOnMouseClicked(event -> {
				ties[x].close();
				if (sceneGuider >= 7 && sceneGuider < 13) {
					ps[x].open();					
					sceneGuider++;
				} else if (sceneGuider == 13) {
//					System.out.println("alarm clock");
					sceneGuider++;
					GameLogic.getStage().setScene((new Scene6()).getOverall());
				}
			});
		}
		
//		keyboard.setOnMouseClicked(event -> {
//		    if (sceneGuider == 3) {
//		        keyboard.close();
//		        runGlitchEffect();
//		    }
//		});
		
//		this.setCursor(Cursor.HAND);
		Rectangle fadeOverlay = new Rectangle(900, 650, Color.BLACK);
		this.getChildren().addAll(fadeOverlay, GameLogic.getBlinker().blackScene);
		FadeTransition sceneFadeIn = new FadeTransition(Duration.seconds(3), fadeOverlay);
        sceneFadeIn.setFromValue(1.0); 
        sceneFadeIn.setToValue(0.0);   
        sceneFadeIn.setDelay(Duration.seconds(2));
        sceneFadeIn.setOnFinished(event -> this.getChildren().remove(fadeOverlay)); // Remove after fade
        sceneFadeIn.play(); 
		

	}
	
	private void runGlitchEffect() {
	    comsmile[0].open();
	    comsmile[1].open();
	    comsmile[2].open();

	    Timeline glitchEffect = new Timeline(
	        new KeyFrame(Duration.millis(100), e -> {
	            comsmile[0].setVisible(true);
	            comsmile[1].setVisible(false);
	            comsmile[2].setVisible(false);
	        }),
	        new KeyFrame(Duration.millis(200), e -> {
	            comsmile[0].setVisible(false);
	            comsmile[1].setVisible(true);
	            comsmile[2].setVisible(false);
	        }),
	        new KeyFrame(Duration.millis(300), e -> {
	            comsmile[0].setVisible(false);
	            comsmile[1].setVisible(false);
	            comsmile[2].setVisible(true);
	        })
	    );

	    glitchEffect.setCycleCount(10);
	    glitchEffect.setOnFinished(e -> {
	        comsmile[0].close();
	        comsmile[1].close();
	        comsmile[2].close();
	        setBackground(backgrounds[2]); // backgrounds[2] = "scene5_black"
	        blackscreen.open();
	        sceneGuider = 4;
	    });

	    glitchEffect.play();
	}

}
