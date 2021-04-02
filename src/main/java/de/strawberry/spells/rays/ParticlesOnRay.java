package de.strawberry.spells.rays;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import java.util.function.Predicate;

public class ParticlesOnRay {

    public void spawnParticleAlongLine(Location start, Location end, Particle particle, int pointsPerLine, int particleCount, float offsetX, float offsetY, float offsetZ, float speed,boolean forceDisplay, Predicate<Location> operationPerPoint) {
        double d = start.distance(end) / pointsPerLine;
        for (int i = 0; i < pointsPerLine; i++) {
            Location l = start.clone();
            Vector direction = end.toVector().subtract(start.toVector()).normalize();
            Vector v = direction.multiply(i * d);
            l.add(v.getX(), v.getY(), v.getZ());
            if (operationPerPoint == null) {
                Bukkit.getWorld("world").spawnParticle(particle, l, particleCount,offsetX, offsetY, offsetZ, speed, null, forceDisplay);
                continue;
            }
            if (operationPerPoint.test(l)) {
                Bukkit.getWorld("world").spawnParticle(particle, l, particleCount,offsetX, offsetY, offsetZ, speed, null, forceDisplay);
            }
        }
    }
}
