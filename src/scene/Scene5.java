package scene;

import component.ImageObject;
import component.NoisyObject;
import javafx.scene.layout.Background;

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

	private ImageObject[] ties, ps, comsmile, projector;
	private ImageObject blackscreen, keyboard, monitor, mouse, timetohavefun, welcomestupid;
	
	private Background[] backgrounds;
	int order = 0;
	public Scene5() {
		super("scene5/BG_scene5_2.png");
		String[] bg = {"1", "black", "red"};
		backgrounds = new Background[3];
		for (int i=0; i<3; i++) {
			backgrounds[i] = createBackground("scene5/BG_scene5_" + bg[i] + ".png");
		}
		
		ties = new ImageObject[7];
		ps = new ImageObject[7];
		comsmile = new ImageObject[3];
		projector = new ImageObject[2];
		for (int i=0; i<7; i++) {
			ties[i] = new ImageObject("scene5/object/tie" + (i+1) +".png");
			ps[i] = new ImageObject("scene5/object/p" + (i+1) +".png");
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
		
//		this.getChildren().addAll(ps);
		NoisyObject temp = new NoisyObject("scene5/object/blackscreen.png", getAccessibleHelp());
		
		this.setOnMouseClicked(event -> {
				
				this.setBackground(backgrounds[(order++)%3]);
				
		});
	}

}
