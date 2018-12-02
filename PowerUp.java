package sample;

/**
 * Container class for all powerups
 */
public abstract class PowerUp implements Collidable
{
    protected boolean isActivated;
    protected  int timeDuration;

    protected abstract  void activate();

    protected abstract  void deactivate();

    protected boolean checkValidityNow()
    {
        return true;
    }

    public int getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(int timeDuration) {
        this.timeDuration = timeDuration;
    }
}
