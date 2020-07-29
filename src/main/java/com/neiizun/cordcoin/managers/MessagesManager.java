package com.neiizun.cordcoin.managers;

import com.neiizun.cordcoin.enums.emojis.Emoji;

import java.util.HashMap;
import java.util.Map;

public class MessagesManager {
    private final Map<String, String> messages;

    public MessagesManager() {
        this.messages = new HashMap<>();
        createMessages();
    }

    private void createMessages() {
        messages.put("unknown_profile", Emoji.OpenFileFolder + " Ooh Ohh, looks like this user doesn't have a CordCoin profile !");
        messages.put("unknown_self_profile", Emoji.OpenFileFolder + " Ooh Ohh, looks like you don't have a CordCoin profile !");
        messages.put("already_have_profile", Emoji.Newspaper + " Hmm.. you already have a profile !");
        messages.put("successfully_started", Emoji.Computer + " Profile successfully created !, you can now buy your first miner machine ! ");
        messages.put("pay_syntax", Emoji.Warning + " Please, use pay <user> <number>");
        messages.put("not_a_number", Emoji.Cookie + " Select a valid number");
        messages.put("no_many_money", Emoji.Warning + " You don't have enough money !");
        messages.put("successfully_paid", Emoji.MoneyWithWings + " Successfully paid "+Emoji.BallotBoxWithCheck);
    }

    public String getMessage(String messageName) {
        return messages.get(messageName);
    }
}
