package advancedWidget;

import java.util.Enumeration;
import java.util.Hashtable;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ImageFactory {
//	private����������ֹ���������ʵ��
	private ImageFactory() {}
	
//	ͼƬ����ľ��Ե�ַ
	public static final String REAL_PATH = "D:\\myProgram\\e-workspace\\"
			+ "SWTLearning\\advancedWidget\\res\\";
//	ͼƬ���Ƶĳ���
	public static final String XIAO = "1.png";
	public static final String YINXIAN = "2.png";
	public static final String HUAJI = "3.png";
	public static final String PEN = "4.png";
	
//	ʹ��HashTable����ʹ�õ�ͼƬ��Դ
	private static Hashtable<String, Image> htImage = new Hashtable<>();
	/*
	 * ����ͼƬ���ȴ�htImage�л�ȡ��û�еĻ��ټ���
	 * �µ�ͼƬ������Ž�htImage
	 */
	public static Image loadImage(Display display,String imageName) {
		Image image = htImage.get(imageName.toUpperCase());
		if(image==null) {
			image=new Image(display, REAL_PATH+imageName);
			htImage.put(imageName.toUpperCase(), image);
		}
		
		return image;
	}
	
//	�ͷŹ�ϣ���е�ͼƬ��Դ
	public static void dipose() {
		Enumeration<?> e = htImage.elements();
		while (e.hasMoreElements()) {
			Image image = (Image)e.nextElement();
			if(!image.isDisposed())
				image.dispose();
		}
	}
}
