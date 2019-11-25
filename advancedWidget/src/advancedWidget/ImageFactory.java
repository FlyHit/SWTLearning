package advancedWidget;

import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ImageFactory {
//	private构造器，禁止创建该类的实例
	private ImageFactory() {}
	
//	图片保存的绝对地址
	public static final String REAL_PATH = "D:\\myProgram\\e-workspace\\"
			+ "SWTLearning\\advancedWidget\\res\\";
//	图片名称的常量
	public static final String XIAO = "1.png";
	public static final String YINXIAN = "2.png";
	public static final String HUAJI = "3.png";
	public static final String PEN = "4.png";
	
//	使用HashTable保存使用的图片资源
	private static Hashtable<String, Image> htImage = new Hashtable<>();
	/*
	 * 加载图片：先从htImage中获取，没有的话再加载
	 * 新的图片并将其放进htImage
	 */
	public static Image loadImage(Display display,String imageName) {
		Image image = htImage.get(imageName.toUpperCase());
		if(image==null) {
			image=new Image(display, REAL_PATH+imageName);
			htImage.put(imageName.toUpperCase(), image);
		}
		
		return image;
	}
	
//	释放哈希表中的图片资源
	public static void dipose() {
		Enumeration<?> e = htImage.elements();
		while (e.hasMoreElements()) {
			Image image = (Image)e.nextElement();
			if(!image.isDisposed())
				image.dispose();
		}
	}
}
