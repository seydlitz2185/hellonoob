package creatures;

import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;

/**
 *
 * @author Dr.Seydlitz
 * @date 2021/10/5 21:16
 */
public class Clorus extends Creature{
    protected double minEnergy = 0;
    /**
     * red color.
     */
    private int r ;
    /**
     * green color.
     */
    private int g ;
    /**
     * blue color.
     */
    private int b ;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }
    public Clorus() {
        this(2);
    }

    @Override
    public void move() {
        changeEnergy(-0.03);
    }

    @Override
    public void stay() {
        changeEnergy(-0.01);
    }


    public void attack(Creature c) {
        double get = c.energy();
        this.energy += get;
    }

    @Override
    public Clorus replicate() {
        this.energy /= 2;
        Clorus baby= new Clorus(energy);
        return baby;
    }


    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipsNeighbors = new ArrayDeque<>();
        boolean anyPlips = false;
        // TODO
        // (Google: Enhanced for-loop over keys of NEIGHBORS?)
        for (Direction key :neighbors.keySet()) {
            if(neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.add(key);
            }
            else if (neighbors.get(key).name().equals("plip")){
                anyPlips = true;
                plipsNeighbors.add(key);
            }
        }

        if (emptyNeighbors.isEmpty() && plipsNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (anyPlips == true){
            return new Action(Action.ActionType.ATTACK,randomEntry(plipsNeighbors));
        }else if(this.energy >= 1.0){
            this.replicate();
            return new Action(Action.ActionType.REPLICATE,randomEntry(emptyNeighbors));
        }else {
            return new Action(Action.ActionType.MOVE,randomEntry(emptyNeighbors));
        }
    }

    @Override
    public Color color() {
        return color(r, g, b);
    }

    public void changeEnergy(double d){
        this.energy += d;
        if (this.energy <minEnergy){
            this.energy = minEnergy;
        }
    }

}
