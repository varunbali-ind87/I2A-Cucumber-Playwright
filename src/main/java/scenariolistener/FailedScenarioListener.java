package scenariolistener;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestCaseFinished;
import lombok.extern.log4j.Log4j2;

import static browsersetup.PlaywrightBase.Scenario;

@Log4j2
public class FailedScenarioListener implements ConcurrentEventListener
{
    public void onTestFinished(final TestCaseFinished event)
    {
        var result = event.getResult();
        if (result.getStatus().is(Status.FAILED))
        {
            final var throwable = result.getError();
            log.error("Following exception was found in {}", Scenario().getName());
            log.error(throwable.getStackTrace());
        }

    }

    @Override
    public void setEventPublisher(final EventPublisher eventPublisher)
    {
        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::onTestFinished);
    }
}
