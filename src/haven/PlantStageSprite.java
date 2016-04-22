package haven;

import java.awt.*;


public class PlantStageSprite extends Sprite {
    private static final Text.Foundry fndr = new Text.Foundry(Text.sansb, 12).aa(true);
    private static final Text.Foundry fndrmax = new Text.Foundry(Text.sansb, 20).aa(true);
    private static final Color stagecolor = new Color(255, 227, 168);
    private static final Color stagemaxcolor = new Color(254, 100, 100);
    private static final Tex stgmaxtex = Text.renderstroked("\u2022", stagemaxcolor, Color.BLACK, fndrmax).tex();
    private static final Tex[] stgtex = new Tex[]{
            Text.renderstroked("2", stagecolor, Color.BLACK, fndr).tex(),
            Text.renderstroked("3", stagecolor, Color.BLACK, fndr).tex(),
            Text.renderstroked("4", stagecolor, Color.BLACK, fndr).tex(),
            Text.renderstroked("5", stagecolor, Color.BLACK, fndr).tex()
    };
    public int stg;
    private Tex tex;
    GLState.Buffer buf;

    public PlantStageSprite(int stg, int stgmax) {
        super(null, null);
        update(stg, stgmax);
    }

    public void draw(GOut g) {
        Matrix4f cam = new Matrix4f(), wxf = new Matrix4f(), mv = new Matrix4f();
        mv.load(cam.load(buf.get(PView.cam).fin(Matrix4f.id))).mul1(wxf.load(buf.get(PView.loc).fin(Matrix4f.id)));
        Coord3f s = buf.get(PView.proj).toscreen(mv.mul4(Coord3f.o), buf.get(PView.wnd).sz());
        g.image(tex, new Coord((int) s.x - 15, (int) s.y - 20));
    }

    public boolean setup(RenderList rl) {
        rl.prepo(last);
        buf = rl.state().copy();
        return true;
    }

    public void update(int stg, int stgmax) {
        this.stg = stg;
        tex = stg == stgmax ? stgmaxtex : stgtex[stg - 1];
    }
}