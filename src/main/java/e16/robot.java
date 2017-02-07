package e16;
import robocode.*;
//import java.awt.Color;

// API help : robocode.sourceforge.net/docs/robocode/…

/**
 * E145741_165750 - a robot by (your name here)
 */
import robocode.AdvancedRobot;
import robocode.HitRobotEvent;
import robocode.ScannedRobotEvent;
import static robocode.util.Utils.normalRelativeAngleDegrees;
import java.awt.*;

/**
 * SpinBot - a sample robot by Mathew Nelson.
 * <p/>
 * Moves in a circle, firing hard when an enemy is detected.
 *
 * @author Mathew A. Nelson (original)
 * @author Flemming N. Larsen (contributor)
 */
public class E145741_165750 extends AdvancedRobot
{
/**
 * run: E145741_165750's default behavior
 */
    /**
     * SpinBot's run method - Circle
     */
    public void run() {//このロボットの基本的な動き
        //ロボットの色・・・all white
        setBodyColor(Color.white);
        setGunColor(Color.white);
        setRadarColor(Color.white);
        setScanColor(Color.white);

        while (true) {// ループでずっと回らせる

            setTurnRight(10000);//右に10000回転

            setMaxVelocity(8);;//ロボットのスピードを8未満に制限する

            ahead(10000);//前方に10000進む

        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {//・・onScannedRobot():レーダーが的ロボットをスキャンした時のメソッド //発射メソッドを呼び出す
        smartFire(e.getDistance());//getDistanceで相手との距離を取得
    }

    public void smartFire(double robotDistance) {//距離により大砲の威力を変えるメソッド
        if(robotDistance > 200 || getEnergy() < 15){//距離が200より大きい時に
            fire(1);//1の攻撃
        }else if (robotDistance > 50){//50より大きい時に
            fire(2);//2の攻撃
        }else{//それ以外(近距離)
            fire(3);//3の攻撃
        }
    }
    public void onHitRobot(HitRobotEvent e) {//他のロボットに当たった時のメソッド
        if (e.getBearing() > -10 && e.getBearing() < 10) {//-10~10の範囲に敵がいるとき
            fire(3);//3で攻撃
        }
        if (e.isMyFault()) {//自分が相手に向かっていた時
            turnRight(10);//右に10回転して逃げる
        }
    }
}