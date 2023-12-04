package interface_adapter.main_menu;

import org.junit.jupiter.api.*;
import use_case.main_menu.*;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuControllerTest {

    private MainMenuController controller;
    private DummySwitchToGalleryInteractor galleryInteractor;
    private DummySwitchToGamblingInteractor gamblingInteractor;
    private DummySwitchToGenerateCardInteractor generateCardInteractor;
    // Include other interactors as necessary

    @BeforeEach
    void setUp() {
        galleryInteractor = new DummySwitchToGalleryInteractor();
        gamblingInteractor = new DummySwitchToGamblingInteractor();
        generateCardInteractor = new DummySwitchToGenerateCardInteractor();
        // Initialize other interactors as necessary

        controller = new MainMenuController(galleryInteractor, gamblingInteractor, generateCardInteractor, null); // Assuming logout interactor is not used in the test
    }

    @Test
    void testExecuteGallery() {
        controller.executeGallery();
        assertTrue(galleryInteractor.isExecuted());
    }

    @Test
    void testExecuteGambling() {
        controller.executeGambling();
        assertTrue(gamblingInteractor.isExecuted());
    }

    @Test
    void testExecuteGenerateCard() {
        controller.executeGenerateCard();
        assertTrue(generateCardInteractor.isExecuted());
    }

    // Additional tests for any other methods

    private static class DummySwitchToGalleryInteractor implements SwitchToGalleryInputBoundary {
        private boolean executed = false;

        @Override
        public void execute() {
            executed = true;
        }

        public boolean isExecuted() {
            return executed;
        }
    }

    private static class DummySwitchToGamblingInteractor implements SwitchToGamblingInputBoundary {
        private boolean executed = false;

        @Override
        public void execute() {
            executed = true;
        }

        public boolean isExecuted() {
            return executed;
        }
    }

    private static class DummySwitchToGenerateCardInteractor implements SwitchToGenerateCardInputBoundary {
        private boolean executed = false;

        @Override
        public void execute() {
            executed = true;
        }

        public boolean isExecuted() {
            return executed;
        }
    }

    // Include other dummy interactor classes as necessary
}
