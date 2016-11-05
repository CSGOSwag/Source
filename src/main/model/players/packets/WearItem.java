package main.model.players.packets;

import main.model.players.Client;
import main.model.players.PacketType;

/**
 * Wear Item
 **/
public class WearItem implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		c.wearId = c.getInStream().readUnsignedWord();
		c.wearSlot = c.getInStream().readUnsignedWordA();
		c.interfaceId = c.getInStream().readUnsignedWordA();
		c.alchDelay = System.currentTimeMillis();
		if (!c.getItems().playerHasItem(c.wearId, 1)) {
			return;
		}
		 if(!c.getItems().playerHasItem(c.wearId, 1, c.wearSlot)) {
            return;
        }
		if ((c.playerIndex > 0 || c.npcIndex > 0) && c.wearId != 4153)
			c.getCombat().resetPlayerAttack();
		if (c.canChangeAppearance) {
			c.sendMessage("You can't wear an item while changing appearence.");
			return;
		}
		if (c.wearId >= 5509 && c.wearId <= 5515) {
			int pouch = -1;
			int a = c.wearId;
			if (a == 5509)
				pouch = 0;
			if (a == 5510)
				pouch = 1;
			if (a == 5512)
				pouch = 2;
			if (a == 5514)
				pouch = 3;
			c.getPA().emptyPouch(pouch);
			return;
		}
		c.getItems().wearItem(c.wearId, c.wearSlot);
	}

}