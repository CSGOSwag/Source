package main.model.players.packets;

import main.model.items.UseItem;
import main.model.players.Client;
import main.model.players.PacketType;
import main.model.players.skills.Cooking;

public class ItemOnObject implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		/*
		 * a = ? b = ?
		 */

		c.getInStream().readUnsignedWord();
		int objectId = c.getInStream().readSignedWordBigEndian();
		int objectY = c.getInStream().readSignedWordBigEndianA();
		c.getInStream().readUnsignedWord();
		int objectX = c.getInStream().readSignedWordBigEndianA();
		int itemId = c.getInStream().readUnsignedWord();
		if (!c.getItems().playerHasItem(itemId, 1)) {
			return;
		}
		UseItem.ItemonObject(c, objectId, objectX, objectY, itemId);
		switch (objectId) {
		/*
		 * case ###: //Glory recharging if (itemId == 1710 || itemId == 1708 ||
		 * itemId == 1706 || itemId == 1704) { int amount =
		 * (c.getItems().getItemCount(1710) + c.getItems().getItemCount(1708) +
		 * c.getItems().getItemCount(1706) + c.getItems().getItemCount(1704));
		 * int[] glories = {1710, 1708, 1706, 1704}; for (int i : glories) {
		 * c.getItems().deleteItem(i, c.getItems().getItemCount(i)); }
		 * c.startAnimation(832); c.getItems().addItem(1712, amount); } break;
		 */
		case 12269:
		case 2732:
		case 3039:
		case 114:
		case 2728:
			Cooking.cookThisFood(c, itemId, objectId);
			break;
		}

	}

}
