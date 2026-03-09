//new JFrame - окно
//new JMenu - панель меню
//new JMenuBar - окно меню
//new JMenuItem - элемент окна меню
package gui;
import log.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;


public class MenuButton {
    private final JFrame parentFrame;
    public MenuButton(JFrame parentFrame){
        this.parentFrame = parentFrame;
    }
    public JMenuBar generateMenuBar()
    {
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(lookAndFeelMenu());
        menuBar.add(testMenu());
        menuBar.add(closeMenu());
        return menuBar;
    }
    private JMenu lookAndFeelMenu() {
        JMenu lookAndFeelMenu = new JMenu("Режим отображения");
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.getAccessibleContext().setAccessibleDescription(
                "Управление режимом отображения приложения");
        lookAndFeelMenu.add(systemLookAndFeel());
        lookAndFeelMenu.add(crossplatformLookAndFeel());
        return lookAndFeelMenu;
    }
    private JMenuItem systemLookAndFeel(){
        JMenuItem systemLookAndFeel = new JMenuItem("Системная схема", KeyEvent.VK_S);
        systemLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        });
        return systemLookAndFeel;
    }
    private JMenuItem crossplatformLookAndFeel() {
        JMenuItem crossplatformLookAndFeel = new JMenuItem("Универсальная схема", KeyEvent.VK_S);
        crossplatformLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        });
        return crossplatformLookAndFeel;
    }
    private void setLookAndFeel(String className)
    {
        try
        {
            UIManager.setLookAndFeel(className);
            SwingUtilities.updateComponentTreeUI(parentFrame);
        }
        catch (ClassNotFoundException | InstantiationException
               | IllegalAccessException | UnsupportedLookAndFeelException e)
        {
            // just ignore
        }
    }

    private JMenu testMenu(){
        JMenu testMenu = new JMenu("Тесты");
        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.getAccessibleContext().setAccessibleDescription(
                "Тестовые команды");
        testMenu.add(addLogMessageItem());
        return testMenu;
    }

    private JMenuItem addLogMessageItem(){
        JMenuItem addLogMessageItem = new JMenuItem("Сообщение в лог", KeyEvent.VK_S);
        addLogMessageItem.addActionListener((event) -> {
            Logger.debug("Новая строка");
        });
        return addLogMessageItem;
    }
    private JMenu closeMenu(){
        JMenu closeMenu = new JMenu("Закрыть");
        closeMenu.setMnemonic(KeyEvent.VK_V);
        closeMenu.add(questionClose());
        return closeMenu;
    }

    private JMenuItem questionClose(){
        JMenuItem checkCloseMenu = new JMenuItem("Вы уверены?", KeyEvent.VK_S);
        checkCloseMenu.addActionListener((event)-> {
            ((MainApplicationFrame) parentFrame).closeApplication();
        });
        return checkCloseMenu;
    }


}
