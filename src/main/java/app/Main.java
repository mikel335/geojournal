package app;

import javax.swing.JFrame;

/**
 * The Main class of our application.
 */
public class Main {
    /**
     * Builds and runs the CA architecture of the application.
     */
    public static void main(String[] args) {
        final Builder builder = new Builder();
        final JFrame application = builder
                .addEntryListView()
                .addEditImagesView()
                .addUpdateCoordsView()
                .addUpdateTextView()
                .addWeatherViewModel()
                .addViewEntryView()
                .addCreateEntryUseCase()
                .addChangeSortUseCase()
                .addWeatherUseCase()
                .addViewEntryUseCase()
                .addEditImagesUseCase()
                .addUpdateTextUseCase()
                .addUpdateCoordsUseCase()
                .addOpenEntryUseCase()
                .build();

        application.pack();
        application.setVisible(true);
    }
}