package com.util;

import java.awt.AWTException;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @Description:图片处理工具
 * @author:liuyc
 * @time:2016年5月27日 上午10:18:00
 */
public class ImageHandleHelper {

	/**
	 * 将图片截成圆的
	 * 
	 * @param picturePath
	 * @param width
	 * @param height
	 *            宽+高组成的方形大小圆
	 * @param result
	 * @throws Exception
	 */
	public static void Beround(String picturePath, String result)
			throws Exception {
		// BufferedImage bi1 = ImageIO.read(new File(picturePath));
		int w = 270, h = 270;// 新图片宽高
		// 将图片等比缩小
		compressPic(picturePath, result, w, h, false);
		BufferedImage newPath = ImageIO.read(new File(result));
		// if(bi1.getWidth()<=bi1.getHeight()){
		// w=h=bi1.getWidth();
		// }else{
		// w=h=bi1.getHeight();
		// }
		// int a=0;
		// int b=0;//截图起点
		// if(bi1.getWidth()<=bi1.getHeight()){
		// b=(bi1.getHeight()-w)/2;
		// }else{
		// a=(bi1.getWidth()-h)/2;
		// }
		// //先弄成正方形
		// String newPicture= cutImage(picturePath, result, a, b, w, h);
		// 根据需要是否使用 BufferedImage.TYPE_INT_ARGB
		BufferedImage bi2 = new BufferedImage(newPath.getWidth(),
				newPath.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, w, h);

		Graphics2D g2 = bi2.createGraphics();
		// g2.setBackground(Color.WHITE);
		g2.setComposite(AlphaComposite.Clear);
		g2.fill(new Rectangle(0, 0));
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC, 1.0f));
		g2.setClip(shape);
		// 使用 setRenderingHint 设置抗锯齿
		g2.drawImage(newPath, 0, 0, null);
		g2.dispose();
		try {
			ImageIO.write(bi2, "png", new File(result));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 屏幕截图
	 * 
	 * @param x左上角X坐标
	 * @param y左上角Y坐标
	 * @param width
	 * @param height
	 * @param path路径
	 */
	public static void cutImg(int x, int y, int width, int height, String path) {
		try {
			Robot robot = new Robot();
			String name = IdGenerator.genTradeNum() + ".jpg";
			BufferedImage bi = robot.createScreenCapture(new Rectangle(0, 0,
					500, 500)); // 根据指定的区域(1300,800)抓取屏幕的指定区域
			ImageIO.write(bi, "jpg", new File(path + name)); // 把抓取到的内容写入到一个jpg文件中
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Description:截图
	 * @author:liuyc
	 * @time:2016年5月27日 上午10:18:23
	 * @param srcFile源图片
	 *            、targetFile截好后图片全名、startAcross
	 *            开始截取位置横坐标、StartEndlong开始截图位置纵坐标、width截取的长，hight截取的高
	 */
	public static String cutImage(String srcFile, String targetFile,
			int startAcross, int StartEndlong, int width, int hight)
			throws Exception {
		// 取得图片读入器
		Iterator<ImageReader> readers = ImageIO
				.getImageReadersByFormatName("jpg");
		ImageReader reader = readers.next();
		// 取得图片读入流
		InputStream source = new FileInputStream(srcFile);
		ImageInputStream iis = ImageIO.createImageInputStream(source);
		reader.setInput(iis, true);
		// 图片参数对象
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(startAcross, StartEndlong, width, hight);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, targetFile.split("\\.")[1], new File(targetFile));
		return targetFile;
	}

	/**
	 * @Description:图片拼接 （注意：必须两张图片长宽一致哦）
	 * @author:liuyc
	 * @time:2016年5月27日 下午5:52:24
	 * @param files
	 *            要拼接的文件列表
	 * @param type1
	 *            横向拼接， 2 纵向拼接
	 */
	public static void mergeImage(String[] files, int type, String targetFile) {
		int len = files.length;
		if (len < 1) {
			throw new RuntimeException("图片数量小于1");
		}
		File[] src = new File[len];
		BufferedImage[] images = new BufferedImage[len];
		int[][] ImageArrays = new int[len][];
		for (int i = 0; i < len; i++) {
			try {
				src[i] = new File(files[i]);
				images[i] = ImageIO.read(src[i]);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			int width = images[i].getWidth();
			int height = images[i].getHeight();
			ImageArrays[i] = new int[width * height];
			ImageArrays[i] = images[i].getRGB(0, 0, width, height,
					ImageArrays[i], 0, width);
		}
		int newHeight = 0;
		int newWidth = 0;
		for (int i = 0; i < images.length; i++) {
			// 横向
			if (type == 1) {
				newHeight = newHeight > images[i].getHeight() ? newHeight
						: images[i].getHeight();
				newWidth += images[i].getWidth();
			} else if (type == 2) {// 纵向
				newWidth = newWidth > images[i].getWidth() ? newWidth
						: images[i].getWidth();
				newHeight += images[i].getHeight();
			}
		}
		if (type == 1 && newWidth < 1) {
			return;
		}
		if (type == 2 && newHeight < 1) {
			return;
		}

		// 生成新图片
		try {
			BufferedImage ImageNew = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_RGB);
			int height_i = 0;
			int width_i = 0;
			for (int i = 0; i < images.length; i++) {
				if (type == 1) {
					ImageNew.setRGB(width_i, 0, images[i].getWidth(),
							newHeight, ImageArrays[i], 0, images[i].getWidth());
					width_i += images[i].getWidth();
				} else if (type == 2) {
					ImageNew.setRGB(0, height_i, newWidth,
							images[i].getHeight(), ImageArrays[i], 0, newWidth);
					height_i += images[i].getHeight();
				}
			}
			// 输出想要的图片
			ImageIO.write(ImageNew, targetFile.split("\\.")[1], new File(
					targetFile));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:小图片贴到大图片形成一张图(合成)
	 * @author:liuyc
	 * @time:2016年5月27日 下午5:51:20
	 */
	public static final void QRoverlapImage(String bigPath, String smallPath,
			String outFile, int x, int y, int width, int height) {
		try {
			BufferedImage big = ImageIO.read(new File(bigPath));
			BufferedImage small = ImageIO.read(new File(smallPath));
			Graphics2D g = big.createGraphics();
			g.drawImage(small, x, y, width, height, null);
			g.dispose();
			ImageIO.write(big, outFile.split("\\.")[1], new File(outFile));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static final void overlapImage(String bigPath, String smallPath,
			String outFile, int x, int y) {
		try {
			BufferedImage big = ImageIO.read(new File(bigPath));
			BufferedImage small = ImageIO.read(new File(smallPath));
			Graphics2D g = big.createGraphics();
			g.drawImage(small, x, y, small.getWidth(), small.getHeight(), null);
			g.dispose();
			ImageIO.write(big, outFile.split("\\.")[1], new File(outFile));
			big.flush();
			small.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * @Description:给图片添加文字信息
	 * @author:liuyc
	 * @time:2016年5月31日 上午10:23:36
	 */
	public static void drawStringForImage(String filePath, String content,
			Color contentColor, float qualNum, String targetFile, int widths,
			int heights, int size) {
		ImageIcon imgIcon = new ImageIcon(filePath);
		Image theImg = imgIcon.getImage();
		int width = theImg.getWidth(null) == -1 ? 200 : theImg.getWidth(null);
		int height = theImg.getHeight(null) == -1 ? 200 : theImg
				.getHeight(null);
		BufferedImage bimage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bimage.createGraphics();
		g.setColor(contentColor);
		g.setBackground(Color.red);
		g.drawImage(theImg, 0, 0, null);
		// 设置字体、字型、字号
		g.setFont(new Font("宋体", Font.BOLD, size));

		// 写入文字

		g.drawString(content, (width - size * content.length()) / 2 + widths,
				heights);
		g.dispose();
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(targetFile);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(qualNum, true);
			encoder.encode(bimage, param);
			out.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					out = null;
					throw new RuntimeException(e);
				}
			}
		}
	}

	/**
	 * @param inputFile源文件
	 * @param outFile生成文件
	 * @param width指定宽度
	 * @param height指定高度
	 * @param proportion是否等比例操作
	 * @return
	 * @作者 王建明
	 * @创建日期 2012-8-2
	 * @创建时间 下午02:02:38
	 * @描述 —— 是否等比例缩放图片
	 */
	public static String compressPic(String inputFile, String outFile,
			int width, int height, boolean proportion) {
		try {
			// 获得源文件
			File file = new File(inputFile);

			if (!file.exists()) {

				return "";
			}

			Image img = ImageIO.read(file);
			// 判断图片格式是否正确

			if (img.getWidth(null) == -1) {

				return "";
			} else {

				int newWidth;
				int newHeight;
				// 判断是否是等比缩放

				if (proportion == true) {

					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null))
							/ (double) width + 0.1;

					double rate2 = ((double) img.getHeight(null))
							/ (double) height + 0.1;
					// 根据缩放比率大的进行缩放控制

					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				} else {

					newWidth = width; // 输出的图片宽度
					newHeight = height; // 输出的图片高度
				}

				// 如果图片小于目标图片的宽和高则不进行转换
				/*
				 * if (img.getWidth(null) < width && img.getHeight(null) <
				 * height) { newWidth = img.getWidth(null); newHeight =
				 * img.getHeight(null); }
				 */
				BufferedImage tag = new BufferedImage((int) newWidth,
						(int) newHeight, BufferedImage.TYPE_INT_RGB);

				// Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的,优先级比速度高 生成的图片质量比较好 但速度慢
				tag.getGraphics().drawImage(
						img.getScaledInstance(newWidth, newHeight,
								Image.SCALE_SMOOTH), 0, 0, null);

				FileOutputStream out = new FileOutputStream(outFile);
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

				encoder.encode(tag);
				out.close();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "";
	}

	public static void main(String[] args) throws Exception {
//		String a = "101元";
//		drawStringForImage("d:/page1bg.jpg", a, Color.red, 1f, "d:/1.jpg",
//				20 * (a.length() - 2), 350, 60);
		// drawStringForImage("d:/1.jpg", "寻找从事", Color.black, 1f,
		// "d:/2.jpg",0,410,30);
		// drawStringForImage("d:/2.jpg", "寻找从事过金融行业，并熟悉政府资源的", Color.black, 1f,
		// "d:/3.jpg",60,445,30);
		// String QRpath=QRCodeUtil.encode("http://www.baidu.com", null, "d:/",
		// false);
//		QRoverlapImage("d:/3.jpg", "d:/42378.jpg", "d:/4.jpg", 290, 910, 130,
//				130);
		// Beround("d:/20161009_10545560.jpg", "d:/rouund22.jpg");
		// overlapImage("d:/page1bg.jpg", "d:/rouund22.jpg",
		// "d:/newlap.jpg",218,555);
		 Beround("http://localhost:8080/mjlq/img/logo.jpg", "d:/compress2.jpg");
	}

}