package com.test;

import com.test.services.BookService;
import com.test.services.BookServiceImpl;
import com.test.services.Format;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.themes.ValoTheme;
import io.swagger.client.ApiException;
import io.swagger.client.model.Book;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;


public class WindowLayout extends VerticalLayout {

    private Logger logger = Logger.getLogger(WindowLayout.class.getName());

    public WindowLayout() {
        HorizontalLayout headerLayout = createHeaderComponent();
        HorizontalLayout lowerHeaderLayout = createMiddileMenu();
        Image image = getImage();
        HorizontalLayout topicLayout = createMiddleMenu();
        //HorizontalLayout contentLayout = new HorizontalLayout() ;
        CssLayout contentLayout = getContent();


        this.addComponents(headerLayout, lowerHeaderLayout, image, topicLayout, contentLayout);


    }

    public CssLayout getContent() {
        CssLayout mainLayout = new CssLayout();
        mainLayout.setSizeFull();
        mainLayout.setStyleName("maincontent");
        //mainLayout.setStyleName(ValoTheme.TABLE_NO_VERTICAL_LINES);

        CssLayout layout1 = new CssLayout(createLeftContent());
        layout1.setStyleName("layoutleftcontent");

        CssLayout layout2 = new CssLayout(createCenterContent());
        layout2.setStyleName("layoutcentercontent");

        CssLayout layout3 = new CssLayout(getRightContent());
        layout3.setStyleName("layoutrightcontent");

        mainLayout.addComponents(layout1, layout2, layout3);
        // mainLayout.setComponentAlignment(layout1, Alignment.TOP_LEFT);
        // mainLayout.setComponentAlignment(layout2, Alignment.TOP_CENTER);
        // mainLayout.setComponentAlignment(layout3, Alignment.TOP_RIGHT);

        return mainLayout;
    }


    private VerticalLayout getRightContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.setId("contentLayout");

        VerticalLayout layout0 = createRightContent1();
        CssLayout layout1 = new CssLayout(layout0);
        layout1.setId("css1");
        VerticalLayout layout3 = createRightContent2();
        CssLayout layout2 = new CssLayout(layout3);
        layout2.setId("css1");

        layout.addComponents(layout1, layout2);


        return layout;
    }

    private VerticalLayout createRightContent1() {
        VerticalLayout layout1 = new VerticalLayout();
        layout1.setId("leftcontentitem");

        Label caption = new Label("Mail");
        caption.setId("contentCaption");
        caption.setStyleName(ValoTheme.LABEL_H2);

        layout1.addComponent(caption);
        for (int j = 0; j < 5; j++) {
            layout1.addComponent(getContentText("mail" + j, " This is the mail " + j));
        }

        return layout1;

    }

    private VerticalLayout createRightContent2() {
        VerticalLayout layout1 = new VerticalLayout();
        layout1.setSizeFull();
        layout1.setId("leftcontentitem");

        Label caption = new Label("Calender");
        caption.setId("contentCaption");
        caption.setStyleName(ValoTheme.LABEL_H2);

        layout1.addComponent(caption);

        InlineDateField inlineDateField = new InlineDateField();
        inlineDateField.setValue(new java.util.Date());
        inlineDateField.setId("calender");

        layout1.addComponent(inlineDateField);
        layout1.setComponentAlignment(caption,Alignment.TOP_CENTER);
        layout1.setComponentAlignment(inlineDateField,Alignment.TOP_CENTER);


        return layout1;

    }

    private VerticalLayout createLeftContent() {
        VerticalLayout layout = new VerticalLayout();
        layout.setId("contentLayout");

        VerticalLayout layout0 = createLeftContent1();
        CssLayout layout1 = new CssLayout(layout0);
        layout1.setId("css1");
        VerticalLayout layout3 = createLeftContent2();
        CssLayout layout2 = new CssLayout(layout3);
        layout2.setId("css1");

        layout.addComponents(layout1, layout2);


        return layout;
    }


    private VerticalLayout createLeftContent1() {
        VerticalLayout layout1 = new VerticalLayout();
        layout1.setId("leftcontentitem");

        Label caption = new Label("Neuigkeiten - Letzter Monat");
        caption.setId("contentCaption");
        caption.setStyleName(ValoTheme.LABEL_H2);

        layout1.addComponent(caption);
        for (int j = 0; j < 1; j++) {
            layout1.addComponent(getContentText("caption000" + j, " This is the content " + j));
        }

        return layout1;

    }


    private VerticalLayout createCenterContent() {
        VerticalLayout layout1 = new VerticalLayout();
        layout1.setId("centercontentitem");

        Label caption = new Label("Course Info");
        caption.setId("contentCaption");
        caption.setStyleName(ValoTheme.LABEL_H2);
       
        
        layout1.addComponent(new Button("",clickEvent -> clickEvent.getButton()));
        
        layout1.addComponent(caption);
        for (int j = 0; j < 1; j++) {
            layout1.addComponent(getCenterContentText(j, "Course" + j, " This is the course the course information of course  " + j));
        }

        return layout1;

    }


    private VerticalLayout createLeftContent2() {
        VerticalLayout layout1 = new VerticalLayout();
        layout1.setId("leftcontentitem");

        Label caption = new Label("Test - Letzter Monat");
        caption.setId("contentCaption");
        caption.setStyleName(ValoTheme.LABEL_H2);

        layout1.addComponent(caption);
        for (int j = 0; j < 1; j++) {
            layout1.addComponent(getContentText("caption" + j, " This is second content " + j));
        }

        return layout1;

    }

    private VerticalLayout getCenterContentText(int iconId, String caption, String text) {
        VerticalLayout layout = new VerticalLayout();
        layout.setId("content");
        layout.setSizeFull();

        Label label1 = new Label(caption);
        label1.setStyleName(ValoTheme.LABEL_H3);

        Label label3 = new Label();
        label3.setStyleName("iconLabel");
        if (iconId % 2 == 1) {
            label3.setIcon(FontAwesome.USERS);
        } else {
            label3.setIcon(FontAwesome.BOOK);
        }

        Label label2 = new Label(text);
        label2.setStyleName("textContentLabel");
        label2.setStyleName(ValoTheme.LABEL_COLORED);

        Button button = new Button();
        button.setStyleName("centerButton");
        button.setIcon(FontAwesome.ARROW_DOWN);

        HorizontalLayout layout1 = new HorizontalLayout();
        layout1.setStyleName("centerContent");
        layout1.addComponents(label3, label2, button);
        layout1.setSizeFull();

        layout1.setComponentAlignment(label3, Alignment.MIDDLE_LEFT);
        layout1.setComponentAlignment(label2, Alignment.MIDDLE_LEFT);
        layout1.setComponentAlignment(button, Alignment.MIDDLE_RIGHT);

        layout.addComponents(label1, layout1);
        return layout;

    }


    private VerticalLayout getContentText(String caption, String text) {
        VerticalLayout layout = new VerticalLayout();
        layout.setId("content");
        layout.setSizeFull();

        Label label1 = new Label(caption);
        label1.setStyleName(ValoTheme.LABEL_H3);

        Label label2 = new Label(text);
        label2.setStyleName(ValoTheme.LABEL_COLORED);

        layout.addComponents(label1, label2);

        return layout;

    }


    private HorizontalLayout createMiddleMenu() {
        HorizontalLayout middleLayout = new HorizontalLayout();

        Label label = new Label("Ubersicht");
        label.setStyleName(ValoTheme.LABEL_H2);

        Button menuBar = new Button("Kurs 1");
        // menuBar.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        menuBar.setId("menuBar2");
        menuBar.addClickListener(clickEvent -> {
            //Notification.show("geklickt");
            logger.info("Open book ...");
            BookService bookService = null;
            try {
                logger.info("Init bookservice ...");
                bookService = new BookServiceImpl();
                List<Book> books = bookService.getAllBooks();
                logger.info("size of books: " + books.size());
                Book book = books.get(0);
                String bookUrl = bookService.getDownloadUrlOfBook(Format.EPUB, book.getId());
                logger.info("Download url of book: " + bookUrl);
                String epubReader = "https://futurepress.github.io/epubjs-reader/index.html?bookPath=" + bookUrl;
                logger.info("Epub reader path: " + epubReader);

                BrowserFrame browser = new BrowserFrame("Epub Reader", new ExternalResource(epubReader));
                browser.setWidth("800px");
                browser.setHeight("600px");
                middleLayout.addComponent(browser);

                // Create a sub-window and set the content
                Window subWindow = new Window("Sub-window");
                VerticalLayout subContent = new VerticalLayout();
                subContent.setMargin(true);
                subWindow.setContent(subContent);

                // Put the browser frame
                subContent.addComponent(browser);

                // Center it in the browser window
                subWindow.center();

                // Open it in the main UI
                getUI().addWindow(subWindow);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ApiException e) {
                e.printStackTrace();
            }


        });

        middleLayout.addComponents(label, menuBar);

        middleLayout.setComponentAlignment(menuBar, Alignment.MIDDLE_RIGHT);
        middleLayout.setComponentAlignment(label, Alignment.MIDDLE_LEFT);

        middleLayout.setSizeFull();


        return middleLayout;

    }


    private HorizontalLayout createHeaderComponent() {
        HorizontalLayout headerLayout = new HorizontalLayout();

        Label label = new Label("Litello Lerne leichter!");
        label.setStyleName(ValoTheme.LABEL_H1);

        MenuBar menuBar = new MenuBar();
        menuBar.setStyleName(ValoTheme.MENUBAR_BORDERLESS);


        MenuBar.MenuItem menuItem0 = menuBar.addItem("", null);
        menuItem0.setIcon(FontAwesome.ENVELOPE);

        MenuBar.MenuItem menuItem4 = menuBar.addItem("", null);
        menuItem4.setIcon(FontAwesome.SEARCH);

        MenuBar.MenuItem menuItem = menuBar.addItem("HILFE", null);
        menuItem.addItem("test", null);

        MenuBar.MenuItem menuItem2 = menuBar.addItem("AD", null);
        menuItem2.addItem("test1", null);

        headerLayout.addComponents(label, menuBar);

        headerLayout.setComponentAlignment(menuBar, Alignment.TOP_RIGHT);
        headerLayout.setComponentAlignment(label, Alignment.MIDDLE_LEFT);

        headerLayout.setSizeFull();


        return headerLayout;

    }

    private HorizontalLayout createMiddileMenu() {
        int size = 150;

        HorizontalLayout middleMenu = new HorizontalLayout();

        Label label = new Label("");
        label.setStyleName(ValoTheme.LABEL_H3);
        Label labe2 = new Label("");
        labe2.setStyleName(ValoTheme.LABEL_COLORED);
        VerticalLayout layout = new VerticalLayout(label, labe2);
        Button button1 = new Button();

        

        Image image = new Image("", new ThemeResource("circle.png"));
        image.setSizeFull();
        //image.setHeight(300, Unit.PIXELS);

        HorizontalLayout contentLayout = new HorizontalLayout(image, layout);
        contentLayout.setComponentAlignment(image, Alignment.TOP_LEFT);
        contentLayout.setComponentAlignment(layout, Alignment.MIDDLE_LEFT);

        MenuBar menuBar = new MenuBar();
        menuBar.setStyleName(ValoTheme.MENUBAR_BORDERLESS);


        MenuBar.MenuItem menuItem = menuBar.addItem("PERSONLICHER SCHREIBTISCH", null);
        menuItem.addItem("test", null);

        MenuBar.MenuItem menuItem2 = menuBar.addItem("MAGAZIN", null);
        menuItem2.addItem("test1", null);

        middleMenu.addComponents(contentLayout, menuBar);

        middleMenu.setComponentAlignment(menuBar, Alignment.BOTTOM_RIGHT);
        middleMenu.setComponentAlignment(contentLayout, Alignment.BOTTOM_LEFT);

        middleMenu.setSizeFull();
        return middleMenu;
    }

    private Image getImage() {
        Image image = new Image("", new ThemeResource("img.jpg"));
        image.setSizeFull();
        image.setHeight(300, Unit.PIXELS);
        return image;
    }
}
