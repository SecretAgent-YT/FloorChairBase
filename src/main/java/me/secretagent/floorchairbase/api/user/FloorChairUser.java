package me.secretagent.floorchairbase.api.user;

import com.mongodb.client.MongoCursor;
import me.secretagent.floorchairbase.api.FloorChairAPI;
import me.secretagent.floorchairbase.api.user.rank.FloorChairRank;
import org.bson.Document;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

import static com.mongodb.client.model.Filters.*;

public class FloorChairUser {

    private final UUID uuid;
    private final FloorChairAPI api;
    private final Document query;

    public FloorChairUser(UUID uuid, FloorChairAPI api) {
        this.query = new Document("_id", uuid.toString());
        this.uuid = uuid;
        this.api = api;
        if (getDocument() == null) {
            Document document1 = new Document();
            document1.put("_id", uuid.toString());
            document1.put("exp", 0);
            document1.put("rank", FloorChairRank.DEFAULT.toString());
            api.getPlayers().insertOne(document1);
        }
    }

    public int getXP() {
        return (int) getDocument().get("exp");
    }

    public void setXP(int xp) {
        Document document = getDocument();
        document.put("exp", xp);
        update(document);
    }

    public FloorChairRank getRank() {
        return FloorChairRank.valueOf(getDocument().get("rank").toString());
    }

    public void setRank(FloorChairRank rank) {
        Document document = getDocument();
        document.put("rank", rank.toString());
        update(document);
    }

    public void setRank(int level) {
        if (FloorChairRank.getAtLevel(level) == null) {
            return;
        }
        setRank(FloorChairRank.getAtLevel(level));
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    private void update(Document document) {
        api.getPlayers().replaceOne(query, document);
    }

    public Document getDocument() {
        MongoCursor<Document> cursor = api.getPlayers().find().iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            if (doc.get("_id").toString().equals(uuid.toString())) {
                return doc;
            }
        }
        return null;
    }
}
