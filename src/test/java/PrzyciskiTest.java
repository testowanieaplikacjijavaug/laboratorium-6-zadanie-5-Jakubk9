import org.assertj.swing.core.matcher.JButtonMatcher;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class PrzyciskiTest {

    private static final JButtonMatcher B1 = JButtonMatcher.withText("przycisk 1");

    private static final JButtonMatcher B2 = JButtonMatcher.withText("przycisk 2");

    private static final JButtonMatcher B3 = JButtonMatcher.withText("przycisk 3");

    private static final JButtonMatcher Reset = JButtonMatcher.withText("wyczysc licznik");

    private Przyciski przyciski;

    private FrameFixture window;

    @BeforeClass
    public static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @BeforeEach
    public void setUp() {
        przyciski = GuiActionRunner.execute(Przyciski::new);
        window = new FrameFixture(przyciski);
        window.show();
    }

    @Test
    public void checkTitleTest() {
        window.requireTitle("Przyciski");
    }

    @Test
    public void afterClickButton1TextBoxShouldContains1Test() {
        window.button(B1)
                .click();
        Pause.pause();
        window.textBox()
                .requireText("1");
    }

    @Test
    public void afterClickButton2TextBoxShouldContains1Test() {
        window.button(B2)
                .click();
        Pause.pause();
        window.textBox()
                .requireText("1");
    }

    @Test
    public void afterClickButton3TextBoxShouldContains1Test() {
        window.button(B3)
                .click();
        Pause.pause();
        window.textBox()
                .requireText("1");
    }

    @Test
    public void afterClickingButton1BackgroundShouldChangeColorTest() {
        window.button(B1).background().requireEqualTo(Color.blue);
        window.button(B1)
                .click();
        Pause.pause();
        window.button(B1).background().requireEqualTo(Color.yellow);
    }

    @Test
    public void afterClickingButton1TwoTimesBackgroundShouldBackToBlueColorTest() {
        window.button(B1).background().requireEqualTo(Color.blue);
        window.button(B1)
                .click();
        Pause.pause();
        window.button(B1).background().requireEqualTo(Color.yellow);
        window.button(B1)
                .click();
        Pause.pause();
        window.button(B1).background().requireEqualTo(Color.blue);
    }

    @Test
    public void afterClikingResetButtonTextBoxShouldContains0Test() {
        window.button(B1)
                .click();
        window.button(Reset)
                .click();
        Pause.pause();
        window.textBox()
                .requireText("0");
    }


    @Test
    public void afterUse2ButtonsTextBoxShouldContains3Test() {
        window.button(B1)
                .click();
        Pause.pause();
        window.button(B2)
                .click();
        Pause.pause();
        window.textBox()
                .requireText("2");
    }

    @Test
    public void afterCliking10TimesButtonTextBoxShouldContains3Test() {
        for ( int i = 0; i < 5; i++ ) {
            window.button(B1)
                    .click();
            Pause.pause();
        }
        window.textBox()
                .requireText("5");
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
        przyciski = null;
    }

}