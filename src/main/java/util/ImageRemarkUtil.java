/**
 * 
 */
package util;

/**
 * Title: kuangzhishu<br>
 * Description: <br>
 * Copyright: Copyright (c) 2018    <br>
 * Create DateTime: 2018-8-16 下午1:33:54 <br>
 * @author freeway
 */
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.gif4j.GifDecoder;
import com.gif4j.GifEncoder;
import com.gif4j.GifImage;
import com.gif4j.GifTransformer;
import com.gif4j.TextPainter;
import com.gif4j.Watermark;

/**
 * Created by ZXD on 2018/1/18.
 */
public class ImageRemarkUtil {

	// 水印透明度
	private static float alpha = 0.5f;
	// 水印横向位置
	private static int positionWidth = 150;
	// 水印纵向位置
	private static int positionHeight = 300;
	// 水印宽
	private static int width = 80;
	// 水印高
	private static int height = 80;
	// 水印文字字体
	private static Font font = new Font("宋体", Font.BOLD, 72);
	// 水印文字颜色
	private static Color color = Color.red;

	/*********** 普通图片加水印 ***********/

	/**
	 * 
	 * @param alpha
	 *            水印透明度
	 * @param positionWidth
	 *            水印横向位置
	 * @param positionHeight
	 *            水印纵向位置
	 * @param font
	 *            水印文字字体
	 * @param color
	 *            水印文字颜色
	 */
	public static void setImageMarkOptions(float alpha, int positionWidth,
			int positionHeight, int width, int height, Font font, Color color) {
		if (alpha != 0.0f)
			ImageRemarkUtil.alpha = alpha;
		if (positionWidth != 0)
			ImageRemarkUtil.positionWidth = positionWidth;
		if (positionHeight != 0)
			ImageRemarkUtil.positionHeight = positionHeight;
		if (height != 0)
			ImageRemarkUtil.height = height;
		if (width != 0)
			ImageRemarkUtil.width = width;
		if (font != null)
			ImageRemarkUtil.font = font;
		if (color != null)
			ImageRemarkUtil.color = color;
	}

	/**
	 * 给图片添加水印图片
	 * 
	 * @param iconPath
	 *            水印图片路径
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 */
	public static void markImageByIcon(String iconPath, String srcImgPath,
			String targerPath) {
		markImageByIcon(iconPath, srcImgPath, targerPath, null);
	}

	/**
	 * 给图片添加水印图片、可设置水印图片旋转角度
	 * 
	 * @param iconPath
	 *            水印图片路径
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 * @param degree
	 *            水印图片旋转角度
	 */
	public static void markImageByIcon(String iconPath, String srcImgPath,
			String targerPath, Integer degree) {
		OutputStream os = null;
		try {

			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);
			

			// 1、得到画笔对象
			Graphics2D g = buffImg.createGraphics();
			// 2、设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);

			g.drawImage(
					srcImg.getScaledInstance(srcImg.getWidth(null),
							srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
					null);
			// 3、设置水印旋转
			if (null != degree) {
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2,
						(double) buffImg.getHeight() / 2);
			}

			// 4、水印图片的路径 水印图片一般为gif或者png的，这样可设置透明度
			ImageIcon imgIcon = new ImageIcon(iconPath);

			// 5、得到Image对象。
			Image img = imgIcon.getImage();

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));

			Integer X = srcImg.getWidth(null);

			Integer Y = srcImg.getHeight(null);

			// 6、水印图片的位置
			g.drawImage(img, X - (positionWidth + width), Y
					- (positionHeight + height), width, height, null);

			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
			// 7、释放资源
			g.dispose();

			// 8、生成图片
			os = new FileOutputStream(targerPath);
			ImageIO.write(buffImg, "PNG", os);

			System.out.println("图片完成添加水印图片");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	 /**  
     * 给图片添加“图片水印”、可设置图片水印旋转角度  
     * @param iconPath 作为水印的图片路径    
     * @param srcImgPath 源图片路径  
     * @param targerPath 目标图片路径  
     * @param degree 水印图片旋转角度  
     */  
    public static void markImageByIcon2(String iconPath, String srcImgPath,String targerPath, Integer degree) { 
        OutputStream os = null;   
        try { 
            //1、源图片
            Image srcImg = ImageIO.read(new File(srcImgPath)); 
            BufferedImage buffImg = new BufferedImage(
                    srcImg.getWidth(null),srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);   
 
            //2、水印图象：水印一般为gif或者png的，这样可设置透明度   
            ImageIcon imgIcon = new ImageIcon(iconPath);   
            Image img = imgIcon.getImage();// 得到Image对象。
 
            //3、画笔对象
            Graphics2D g2d = buffImg.createGraphics();
            // ----------  增加下面的代码使得背景透明  -----------------
            buffImg = g2d.getDeviceConfiguration().createCompatibleImage(
                    srcImg.getWidth(null),srcImg.getHeight(null),Transparency.TRANSLUCENT);
            g2d.dispose();
            g2d = buffImg.createGraphics();
            // ----------  背景透明代码结束  -----------------
 
            // 设置对线段的锯齿状边缘处理
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,   
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);// 设置对线段的锯齿状边缘处理   
 
            g2d.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), 
                    srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
 
            // 设置水印旋转
            if (null != degree) {
                g2d.rotate(Math.toRadians(degree),(double) buffImg.getWidth()/2,
                        (double) buffImg.getHeight()/2);   
            }
 
            // 透明度
            float alpha = 1.0f;    
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 
 
            // 表示水印图片的位置
            g2d.drawImage(img, 90, 92, null); 
 
            //4、释放资源
            g2d.dispose();
 
            //5、生成图片 
            os = new FileOutputStream(targerPath);
            ImageIO.write(buffImg,"PNG",os);   
 
            System.out.println("图片完成添加Icon印章");   
        } catch (Exception e) {   
            e.printStackTrace();   
        } finally {   
            try {   
                if (null != os)   
                    os.close();   
            } catch (Exception e) {   
                e.printStackTrace();   
            }   
        }   
    }  
	
	/**
	 * 给图片添加水印文字
	 * 
	 * @param logoText
	 *            水印文字
	 * @param srcImgPath
	 *            源图片路径
	 * @param targerPath
	 *            目标图片路径
	 */
	public static void markImageByText(String logoText, String srcImgPath,
			String targerPath) {
		markImageByText(logoText, srcImgPath, targerPath, null);
	}

	/**
	 * 给图片添加水印文字、可设置水印文字的旋转角度
	 * 
	 * @param logoText
	 * @param srcImgPath
	 * @param targerPath
	 * @param degree
	 */
	public static void markImageByText(String logoText, String srcImgPath,
			String targerPath, Integer degree) {

		InputStream is = null;
		OutputStream os = null;
		try {
			// 1、源图片
			Image srcImg = ImageIO.read(new File(srcImgPath));
			BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),
					srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);

			// 2、得到画笔对象
			Graphics2D g = buffImg.createGraphics();
			// 3、设置对线段的锯齿状边缘处理
			g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			g.drawImage(
					srcImg.getScaledInstance(srcImg.getWidth(null),
							srcImg.getHeight(null), Image.SCALE_SMOOTH), 0, 0,
					null);
			// 4、设置水印旋转
			if (null != degree) {
				g.rotate(Math.toRadians(degree),
						(double) buffImg.getWidth() / 2,
						(double) buffImg.getHeight() / 2);
			}
			// 5、设置水印文字颜色
			g.setColor(color);
			// 6、设置水印文字Font
			g.setFont(font);
			// 7、设置水印文字透明度
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));
			// 8、第一参数->设置的内容，后面两个参数->文字在图片上的坐标位置(x,y)
			g.drawString(logoText, positionWidth, positionHeight);
			// 9、释放资源
			g.dispose();
			// 10、生成图片
			os = new FileOutputStream(targerPath);
			ImageIO.write(buffImg, "JPG", os);

			System.out.println("图片完成添加水印文字");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != is)
					is.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (null != os)
					os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*********** 动图加水印 ************/

	/**
	 * 缩放gif图片，直接传的File文件，可设置宽和高
	 */
	public void makeGif(File src, File dest, int width, int height)
			throws IOException {

		GifImage gifImage = GifDecoder.decode(src);// 创建一个GifImage对象.

		GifImage resizeIMG = GifTransformer.resize(gifImage, width, height,
				true);

		GifEncoder.encode(resizeIMG, dest);

	}

	// 缩放gif图片，直接传文件路径，可设置宽和高
	public void makeGif(String src, String dest, int width, int height)
			throws IOException {

		GifImage gifImage = GifDecoder.decode(new File(src));// 创建一个GifImage对象.

		makeGif(new File(src), new File(dest), gifImage.getScreenWidth() / 2,
				gifImage.getScreenHeight() / 2);

	}

	// 缩放gif图片，传文件File文件，不可设置宽和高
	public void makeGif(File src, File dest) throws IOException {

		GifImage gifImage = GifDecoder.decode(src);// 创建一个GifImage对象.

		makeGif(src, dest, gifImage.getScreenWidth() / 2,
				gifImage.getScreenHeight() / 2);

	}

	// 缩放gif图片，传文件路径，不可设置宽和高
	public void makeGif(String src, String dest) throws IOException {

		makeGif(new File(src), new File(dest));

	}

	/**
	 * 动图中加文字水印
	 */
	public static void addTextWatermarkToGif(File src, String watermarkText,
			File dest) throws IOException {

		// 水印初始化、设置（字体、样式、大小、颜色）

		TextPainter textPainter = new TextPainter(new Font("黑体", Font.ITALIC,
				12));

		textPainter.setOutlinePaint(Color.WHITE);

		BufferedImage renderedWatermarkText = textPainter.renderString(
				watermarkText, true);

		// 图片对象
		GifImage gf = GifDecoder.decode(src);

		// 获取图片大小

		int iw = gf.getScreenWidth();

		int ih = gf.getScreenHeight();

		// 获取水印大小

		int tw = renderedWatermarkText.getWidth();

		int th = renderedWatermarkText.getHeight();

		// 水印位置

		Point p = new Point();
		p.x = iw - tw - 5;
		p.y = ih - th - 4;

		// 加水印
		Watermark watermark = new Watermark(renderedWatermarkText, p);
		gf = watermark.apply(GifDecoder.decode(src), true);
		// 输出
		GifEncoder.encode(gf, dest);
	}

	/**
	 * 动图中加图片水印
	 */
	public static void addImageWatermarkToGif(File src, String watermarkPath,
			File dest) {

		try {

			BufferedImage renderedWatermarkText = ImageIO.read(new File(
					watermarkPath));

			// 图片对象
			GifImage gf = GifDecoder.decode(src);

			// 获取图片大小
			int iw = gf.getScreenWidth();
			int ih = gf.getScreenHeight();

			// 获取水印大小
			int tw = renderedWatermarkText.getWidth();
			int th = renderedWatermarkText.getHeight();

			// 水印位置
			Point p = new Point();
			p.x = iw - tw - 20;
			p.y = ih - th - 20;

			// 加水印
			Watermark watermark = new Watermark(renderedWatermarkText, p);
			// 水印透明度
			watermark.setTransparency(1);
			gf = watermark.apply(GifDecoder.decode(src), false);
			// 输出
			GifEncoder.encode(gf, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			// 需要加水印图片的路径
		/*	String srcImgPath = "d:/1.jpg";
			String logoText = "复 印 无 效";
			// 图片水印的路径
			String iconPath = "d:/2.jpg";
			// 添加完水印文件的输出路径
			String targerTextPath = "d:/qie_text.jpg";
			String targerTextPath2 = "d:/qie_text_rotate.jpg";
			String targerIconPath = "d:/qie_icon.jpg";
			String targerIconPath2 = "d:/qie_icon_rotate.jpg";

			System.out.println("给图片添加水印文字开始...");
			// 给图片添加水印文字
			markImageByText(logoText, srcImgPath, targerTextPath);
			// 给图片添加水印文字,水印文字旋转-45
			markImageByText(logoText, srcImgPath, targerTextPath2, -45);
			System.out.println("给图片添加水印文字结束...");

			System.out.println("给图片添加水印图片开始...");
			setImageMarkOptions(0.3f, 1, 1, 0, 0, null, null);*/
			// 给图片添加水印图片
			markImageByIcon2("D:\\watermark.png", "D:\\1.jpg", "D:\\2.png",null);
			
			// 给图片添加水印图片,水印图片旋转-45
			/*markImageByIcon(iconPath, srcImgPath, targerIconPath2, -45);
			System.out.println("给图片添加水印图片结束...");*/

			// 动图添加水印（添加水印动图文件，添加的水印，添加完输出文件）
			/*addTextWatermarkToGif(new File("d:\\1.gif"), "复 印 无 效", new File(
					"d:\\11.gif"));
			addImageWatermarkToGif(new File("d:\\1.gif"),
					"d:\\watermark.png", new File("d:\\12.gif"));*/
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}