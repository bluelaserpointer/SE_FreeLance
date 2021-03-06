package com.example.myapplicationtest1.game.contents.effect;

import android.graphics.Color;

import com.example.myapplicationtest1.R;
import com.example.myapplicationtest1.game.core.GHQ;
import com.example.myapplicationtest1.game.core.GHQObject;
import com.example.myapplicationtest1.game.paint.animation.SerialImageFrame;
import com.example.myapplicationtest1.game.paint.dot.DotPaint;
import com.example.myapplicationtest1.game.paint.text.StringPaint;
import com.example.myapplicationtest1.game.preset.effect.Effect;

import static java.lang.Math.PI;

public class Effects extends Effect {
	public Effects(GHQObject source) {
		super(source);
	}
	/////////////////
	//SparkHitEF
	/////////////////
	public static class SparkHitEF extends Effects {

		public SparkHitEF(GHQObject source) {
			super(source);
			name = "SparkHitEF";
			limitFrame = 10;
			paintScript = new SerialImageFrame(this, 1,
					R.drawable.a1_hit,
					R.drawable.a2_hit,
					R.drawable.a3_hit,
					R.drawable.a4_hit);
			point().stop();
			angle().set(source.point().moveAngle());
		}
		@Override
		public void paint() {
			getDotPaint().dotPaint_turn(point(), angle().get());
		}
		@Override
		public SparkHitEF getOriginal() {
			return new SparkHitEF(shooter);
		}
	}
	/////////////////
	//DamageNumberEF
	/////////////////
	public static class DamageNumberEF extends Effects {
		private final int color;
		public DamageNumberEF(GHQObject source, String str, int color) {
			super(source);
			name = "DamageNumberEF";
			limitFrame = 25;
			this.color = color;
			paintScript = new DotPaint() {
				StringPaint stringPaint1 = new StringPaint(str, color);
				StringPaint stringPaint2 = new StringPaint(str, Color.DKGRAY);
				@Override
				public void dotPaint(int x, int y) {
					stringPaint2.dotPaint(-2, -2);
					stringPaint2.dotPaint(2, 2);
					stringPaint1.dotPaint(0, 0);
				}
				@Override
				public int width() {
					return stringPaint1.width() + 1;
				}
				@Override
				public int height() {
					return stringPaint1.height() + 1;
				}
			};
			point().setSpeed_DA(6 + Math.random(), 2.0*(Math.random() - 0.5) - Math.PI/2);
			point().addY(-25);
			point().addX(-20);
		}
		@Override
		public DamageNumberEF getOriginal() {
		return new DamageNumberEF(shooter, ((StringPaint)paintScript).text, color);
		}
		@Override
		public void idle() {
		super.idle();
		point().addSpeed(0, 0.4);
		}
		private static final double SIZE_RATE = 2.5;
		@Override
		public void paint() {
		final double lifePercent = lifePercent();
		paintScript.dotPaint_rate(point(), SIZE_RATE*Math.max(0.1, lifePercent < 0.5 ? lifePercent : 1.0 - lifePercent));
		}
	}
}
