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

/**
 * Конструктор класса MenuButton
 * @param parentFrame родительское окно, к которому привязывается меню
 * */
public class MenuButton {
    private final JFrame parentFrame;
    /** Создает панель меню
     * Для добавления новых пунктов меню используется add
     * @return готовая панель меню JMenuBar
     * */
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
    /** Реализует пункт меню, отвечающий за отображение
     * С помощью метода setMnemonic и константы KeyEvent.VK_V обнаруживаем нажатие клавиш
     * С помощью метода add добавляем подпункты
     * @return меню выбора режима отображения
     * */
    private JMenu lookAndFeelMenu() {
        JMenu lookAndFeelMenu = new JMenu("Режим отображения");
        lookAndFeelMenu.setMnemonic(KeyEvent.VK_V);
        lookAndFeelMenu.getAccessibleContext().setAccessibleDescription(
                "Управление режимом отображения приложения");
        lookAndFeelMenu.add(systemLookAndFeel());
        lookAndFeelMenu.add(crossplatformLookAndFeel());
        return lookAndFeelMenu;
    }
    /** Реализация подпункта lookAndFeelMenu
     * Используем обработчик событий addActionListener, в котором с помощью
     * лямбда-выражения вызывается метод setLookAndFeel
     * getSystemLookAndFeelClassName - реализует системный внешний вид
     * @return пункт меню для установки системной схемы
     * */
    private JMenuItem systemLookAndFeel(){
        JMenuItem systemLookAndFeel = new JMenuItem("Системная схема", KeyEvent.VK_S);
        systemLookAndFeel.addActionListener((event) -> {
            setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        });
        return systemLookAndFeel;
    }
    /** Реализация подпункта lookAndFeelMenu
     * Используем обработчик событий addActionListener, в котором с помощью
     * лямбда-выражения вызывается метод setLookAndFeel
     * getCrossPlatformLookAndFeelClassName - реализует универсальный внешний вид
     * @return пункт меню для установки универсальной схемы
     * */
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
    /** Создает меню для тестовых команд
     * С помощью setMnemonic и KeyEvent.VK_T устанавливаем быстрый доступ
     * @return меню "Тесты"
     * */
    private JMenu testMenu(){
        JMenu testMenu = new JMenu("Тесты");
        testMenu.setMnemonic(KeyEvent.VK_T);
        testMenu.getAccessibleContext().setAccessibleDescription(
                "Тестовые команды");
        testMenu.add(addLogMessageItem());
        return testMenu;
    }
    /** Реализует добавление сообщения в лог
     * Используем Logger.debug для записи в лог
     * @return пункт меню "Сообщение в лог"
     * */

    private JMenuItem addLogMessageItem(){
        JMenuItem addLogMessageItem = new JMenuItem("Сообщение в лог", KeyEvent.VK_S);
        addLogMessageItem.addActionListener((event) -> {
            Logger.debug("Новая строка");
        });
        return addLogMessageItem;
    }
    /** Создает меню для закрытия приложения
     * @return меню "Закрыть"
     * */
    private JMenu closeMenu(){
        JMenu closeMenu = new JMenu("Закрыть");
        closeMenu.setMnemonic(KeyEvent.VK_V);
        closeMenu.add(questionClose());
        return closeMenu;
    }
    /** Реализует сборку и обработку события выхода из приложения
     * Используя класс JOptionPane, создается диалоговое окно
     * YES_NO_OPTION - реализует кнопки диалога да/нет
     * setDefaultCloseOperation - пользовательское закрытие через крестик
     * dispatchEvent - искусственное закрытие
     * */
    private void closeApplication(){
        int message = JOptionPane.showConfirmDialog(parentFrame, "Вы действительно выходите?", "Подтверждение ",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (message == JOptionPane.YES_OPTION){
            parentFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            parentFrame.dispatchEvent(new WindowEvent(parentFrame, WindowEvent.WINDOW_CLOSING));
        }
    }

    private JMenuItem questionClose(){
        JMenuItem checkCloseMenu = new JMenuItem("Вы уверены?", KeyEvent.VK_S);
        checkCloseMenu.addActionListener((event)-> {closeApplication();});
        return checkCloseMenu;
    }


}
