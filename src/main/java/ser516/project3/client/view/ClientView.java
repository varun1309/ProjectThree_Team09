package ser516.project3.client.view;

import ser516.project3.constants.ClientConstants;
import ser516.project3.interfaces.ViewInterface;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

/**
 * The ClientView class extends JFrame class to create the main clientUI window
 * and add the corresponding listeners to it
 * 
 * @author vsriva12
 *
 */
@SuppressWarnings("serial")
public class ClientView extends JFrame implements ViewInterface {

	private static ClientView clientViewInstance = null;

	private JMenuBar menuBar;
	private JMenu optionsMenu;
	private JMenuItem serverMenuItem;
	private JTabbedPane expressionsEmotionsCombinedTab;

	private HeaderView headerView;
	private PerformanceMetricView performanceMetricView;
	private ExpressionsView expressionsView;

	private final static int FONT_SIZE = 15;
	private final static int FRAME_WIDTH = 1400;
	private final static int FRAME_HEIGHT = 700;

	/**
	 * This method returns instance fof client view.
	 * 
	 * @return clientViewInstance
	 */
	public static ClientView getClientView() {
		if (clientViewInstance == null) {
			clientViewInstance = new ClientView();
		}
		return clientViewInstance;
	}

	/**
	 * Initializes the basic view of Client UI that includes tabs and menu bar.
	 * @param subViews
	 * 
	 */
	@Override
	public void initializeView(ViewInterface subviews[]) {
		headerView = (HeaderView) subviews[0];
		performanceMetricView = (PerformanceMetricView) subviews[1];
		expressionsView = (ExpressionsView) subviews[2];

		createMenuBar();
		createTabs();
		createLayout();
		setJMenuBar(menuBar);

		setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Creates a enu bar with Open server option.
	 */
	private void createMenuBar() {
		menuBar = new JMenuBar();
		optionsMenu = new JMenu(ClientConstants.OPTIONS);
		optionsMenu.setMnemonic(KeyEvent.VK_O);
		serverMenuItem = new JMenuItem(ClientConstants.OPEN_SERVER);
		serverMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		optionsMenu.add(serverMenuItem);
		menuBar.add(optionsMenu);
	}

	/**
	 * Create Performance Metrics and Expressions tab.
	 */
	private void createTabs() {
		expressionsEmotionsCombinedTab = new JTabbedPane();
		expressionsEmotionsCombinedTab.addTab(ClientConstants.PERFORMANCE_METRICS, performanceMetricView);
		expressionsEmotionsCombinedTab.addTab(ClientConstants.EXPRESSIONS, expressionsView);
		expressionsEmotionsCombinedTab.setFont(new Font(ClientConstants.FONT_NAME, Font.BOLD, FONT_SIZE));
	}

	/**
	 * Creates Grid Bag layout to set tabs and menu bar.
	 */
	private void createLayout() {
		setLayout(new GridBagLayout());
		getContentPane().setBackground(Color.decode(ClientConstants.FRAME_COLOR_HEX));
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.weightx = 0.2;
		gridBagConstraints.weighty = 0.2;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		gridBagConstraints.insets = new Insets(15, 10, 10, 10);
		add(headerView, gridBagConstraints);

		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 1;
		gridBagConstraints.weightx = 1;
		gridBagConstraints.weighty = 1;
		gridBagConstraints.insets = new Insets(10, 10, 15, 10);
		add(expressionsEmotionsCombinedTab, gridBagConstraints);
	}

	/** 
	 * Listener when open server option from menu bar is clicked.
	 * 
	 *  @param actionListener
	 */
	public void addServerMenuItemListener(ActionListener actionListener) {
		serverMenuItem.addActionListener(actionListener);
	}

	/**
	 * Listener when any of the component for client JFrame is clicked.
	 * 
	 * @param windowListener
	 */
	public void addClientWindowListener(WindowListener windowListener) {
		addWindowListener(windowListener);
	}

	/**
	 * Listener when tabs are changed.
	 * 
	 * @param changeListener
	 */
	public void addTabbedPaneSelectionListener(ChangeListener changeListener) {
		expressionsEmotionsCombinedTab.addChangeListener(changeListener);
	}
}
