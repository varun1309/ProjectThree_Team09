package ser516.project3.client.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

import ser516.project3.client.view.upperface.LeftEyeBrow;
import ser516.project3.client.view.upperface.RightEyeBrow;
import ser516.project3.interfaces.ViewInterface;
import ser516.project3.model.MessageModel;

@SuppressWarnings("serial")
public class FaceView extends JPanel implements ViewInterface {
	private static Color faceColor = new Color(255, 223, 135);
	private static int width = 300;
	private static int height = 300;

	public FaceView(int width, int height, Color faceColor) {
		this.width = width;
		this.height = height;
		this.faceColor = faceColor;
		setPreferredSize(new Dimension(width, height));
	}

	private static FaceView instance;

	/**
	 * Creates a singleton instance . If exists, returns it, else creates it.
	 * 
	 * @return instance of the LeftEyeBrow
	 */
	public static FaceView getInstance() {

		try {
			if (instance == null) {
				instance = new FaceView(width, height, faceColor);
			}
		} catch (Exception e) {
		}
		return instance;

	}

	@Override
	public void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		graphics.setColor(new Color(96, 85, 46));
		graphics.drawOval(100, 100, width - 100, height - 80);
		//graphics.drawOval(190, 190, width - 270, height - 250);
		graphics.drawPolygon(new int[] {207, 197, 217}, new int[] {205, 245, 245}, 3);
		/*
		 * graphics.setColor(faceColor); graphics.fillOval(100, 100, width - 100, height
		 * - 80);
		 * 
		 * graphics.setColor(new Color(96, 85, 46)); graphics.drawOval(100, 100, width -
		 * 100, height - 80);
		 * 
		 * graphics.setColor(Color.BLACK); graphics.fillOval(150,150,30,50);
		 * graphics.drawArc(150, 130, 30, 30, 0, 180);
		 * 
		 * graphics.setColor(Color.BLACK); graphics.fillOval(210, 150, 30, 50);
		 * graphics.drawArc(210, 130, 30, 30, 0, 180);
		 * 
		 * graphics.setColor(new Color(249, 47, 84)); // graphics.fillArc(150, 200, 100,
		 * 70, 0, 180); graphics.drawArc(150, 200, 100, 70, 180, 180);
		 */
		Graphics2D graphics2D = (Graphics2D) graphics;
		// graphics2D.drawArc(50, 200, 100, 70, 180, 180);
		// graphics2D.draw(new Arc2D.Double(175, 163, 50, 60, 110, -50,Arc2D.OPEN));
		// Shape sp=new LeftEyeBrow(175, 163, 50, 60, 110, -50);
		// graphics2D.draw(new Leb(175, 163, 50, 60, 110, -50));
		graphics2D.draw(LeftEyeBrow.getInstance());
		graphics2D.draw(RightEyeBrow.getInstance());
		graphics2D.setStroke(new BasicStroke(3));
		// graphics2D.fill(new LeftEyeBrow(175, 163, 50, 60, 110, -50));
		// paintComponent(graphics2D);

	}

	public void updateFaceElements(MessageModel messageBean) {
		LeftEyeBrow.getInstance().moveElement("raiseBrow",messageBean.getAbstractExpression("raiseBrow"));
		RightEyeBrow.getInstance().moveElement("raiseBrow",messageBean.getAbstractExpression("raiseBrow"));
		Graphics2D graphics2d = (Graphics2D) getGraphics();
		paintComponent(graphics2d);
	}

	public void setColor(Color newColor) {

		faceColor = newColor;
	}

	@Override
	public void initializeView(ViewInterface[] subViews) {
			
	}
	
	

}