package scene;

import component.ImageObject;
import javafx.animation.FadeTransition;
import javafx.scene.layout.Background;
import javafx.util.Duration;

/*
Scene 4
หน้า BG_scene4_1
       มี text1 ขึ้นให้อ่านแล้วหาย 
		กด door แล้วพาเข้า BG_scene4_2
หน้า BG_scene4_2
      มี text2 ขึ้นให้อ่านแล้วหาย
		มีแป้นขึ้น ซึ่งมีตัวอักษร L,A,T,E,M,P,0(ศูนย์), O (โอ), C,H,-,2,5,1,S,D,R + จับเวลา  ให้ผู้เล่นกด
		ถ้าผู้เล่นกดตัวอักษรถูก (เฉลยคือ CTRL-SP) ภายในเวลาจะตัดเข้าซีน5 ถ้าหมดเวลาก่อนตอบถูกให้มีเสียงกรี้ดแสบหูก่อนเข้าซีน5

 */

public class Scene4 extends ScenePane {
	private Background next_background;
	private ImageObject text, text2, door;
	public Scene4() {
		super("scene4/BG_scene4_1.png");
		next_background = this.createBackground("scene4/BG_scene4_2.png");
		text = new ImageObject("scene4/object/text1.png");
		text2 = new ImageObject("scene4/object/text2.png");
		door = new ImageObject("scene4/object/door.png");
		
		this.getChildren().add(door);
		startTextFade(text);
		
		door.setOnMouseClicked(event -> {
			this.getChildren().remove(door);
			this.setBackground(next_background);
			startTextFade(text2);
		});
	}
	public void startTextFade(ImageObject t) {
        this.getChildren().add(t);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), t);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(1.0);   
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), t);
        fadeOut.setFromValue(1.0); 
        fadeOut.setToValue(0.0);   
        fadeOut.setDelay(Duration.seconds(2)); 
        fadeOut.setOnFinished(event -> this.getChildren().remove(t)); 

        fadeIn.setOnFinished(event -> fadeOut.play()); 
        fadeIn.play(); 
    }
}
