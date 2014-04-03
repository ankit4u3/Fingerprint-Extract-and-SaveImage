
import com.digitalpersona.uareu.*;
import com.digitalpersona.uareu.Fid.Fiv;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class ImagePanel
        extends JPanel {

    private static final long serialVersionUID = 5;
    private BufferedImage m_image;

    public void showImage(Fid image) {
        Fiv view = image.getViews()[0];
        m_image = new BufferedImage(view.getWidth(), view.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        m_image.getRaster().setDataElements(0, 0, view.getWidth(), view.getHeight(), view.getImageData());
        saveBack(m_image);
        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(m_image, 0, 0, null);
    }

    public void saveBack(final BufferedImage m_image) {
        new SwingWorker<Object, Object>() {

            @Override
            protected Object doInBackground() throws Exception {

                try {
                    // retrieve image
                    BufferedImage bi = m_image;
                    File outputfile = new File("saved.png");
                    ImageIO.write(bi, "png", outputfile);
                } catch (IOException e) {

                }
                return null;
            }
        }.execute();

    }
}
