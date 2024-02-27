package fridge.tertainment.GUI;

public class PageManager {

    private static int currentPageIndex;
    private static GUI_frame guiFrame;
    static GUI_page[] pages = new GUI_page[]{
            new GUI_page("Indstillinger"),
            new GridBagLayoutTestPage("Køleskab"),
            new GUI_page("Opskrifter"),
            new GUI_page("Ugeplan"),

    };
    private PageManager() {
    }

    public static int getPageCount() {
        return pages.length;
    }
    public static void changePage(int pageIndex) {
        guiFrame.setCurrentPage(pages[pageIndex]);
        System.out.println(pages[pageIndex].pageTitle);
    }

    public static void setGUI_Frame(GUI_frame frame) {
        guiFrame = frame;
    }


}
