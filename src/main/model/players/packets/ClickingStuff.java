package main.model.players.packets;

import main.model.players.Client;
import main.model.players.PacketType;
import main.model.players.PlayerHandler;
import main.util.Misc;

/**
 * Clicking stuff (interfaces)
 **/
public class ClickingStuff implements PacketType {

	@Override
	public void processPacket(Client c, int packetType, int packetSize) {
		if (c.inTrade) {
			if (!c.acceptedTrade) {
				Client o = (Client) PlayerHandler.players[c.tradeWith];
				o.tradeAccepted = false;
				c.tradeAccepted = false;
				o.tradeStatus = 0;
				c.tradeStatus = 0;
				c.tradeConfirmed = false;
				c.tradeConfirmed2 = false;
				c.sendMessage("@red@Trade has been declined.");
				o.sendMessage("@red@Other player has declined the trade.");
				Misc.println("trade reset");
				c.getTradeAndDuel().declineTrade();
			}
		}
		if (c.isBanking)
            c.isBanking = false;
        if(c.isShopping)
            c.isShopping = false;
		Client o = (Client) PlayerHandler.players[c.duelingWith];
		if (c.duelStatus == 5) {
			return;
		}
		if (o != null) {
			if (c.openDuel && c.duelStatus >= 1 && c.duelStatus <= 4) {
				c.getTradeAndDuel().declineDuel();
				o.getTradeAndDuel().declineDuel();
			}
		}

		if (c.duelStatus == 6) {
			c.getTradeAndDuel().claimStakedItems();
		}

	}

}
