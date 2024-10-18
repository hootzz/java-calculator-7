package calculator;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    @Test
    void 커스텀_구분자_사용() {
        assertSimpleTest(() -> {
            run("//;\\n1");
            assertThat(output()).contains("결과 : 1");
        });
    }

    @Test
    void 이스케이프_문자_구분자_예외처리() {
        assertSimpleTest(() -> {
            assertThatThrownBy(() -> runException("//;\\\\n1;\\2;\\3"))
                    .isInstanceOf(IllegalArgumentException.class);
        });
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() ->
            assertThatThrownBy(() -> runException("-1,2,3"))
                .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_2() {
        assertSimpleTest(() ->
                assertThatThrownBy(() -> runException("1,-2,3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 예외_테스트_3() {
        assertSimpleTest(() ->
                assertThatThrownBy(() ->  runException("//-n1-2-3"))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    void 빈문자열_테스트() {
        assertSimpleTest(() -> {
            run(" ");
            assertThat(output()).contains("결과 : 0");
        });
    }

    @Test
    void 기본구분자_테스트() {
        assertSimpleTest(() -> {
            run("1,2:3");
            assertThat(output()).contains("결과 : 6");
        });
    }



    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
