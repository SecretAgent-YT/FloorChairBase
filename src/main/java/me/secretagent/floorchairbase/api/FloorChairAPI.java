package me.secretagent.floorchairbase.api;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.secretagent.floorchairbase.api.user.FloorChairUser;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.function.Consumer;

public class FloorChairAPI {

    private MongoDatabase database;
    private MongoCollection players;
    private Consumer<FloorChairAPI> consumer = null;

    public FloorChairAPI() {
        connnect();
    }

    public FloorChairAPI(Consumer<FloorChairAPI> consumer) {
        this.consumer = consumer;
        connnect();
    }

    private void connnect() {
        MongoClient mongoClient = new MongoClient();
        database = mongoClient.getDatabase("floorchair");
        players = database.getCollection("players");
        if (consumer != null) consumer.accept(this);
    }

    public MongoCollection getPlayers() {
        return players;
    }

    public FloorChairUser getUser(UUID uuid) {
        return new FloorChairUser(uuid, this);
    }

    public FloorChairUser getUser(Player player) {
        return getUser(player.getUniqueId());
    }

}
