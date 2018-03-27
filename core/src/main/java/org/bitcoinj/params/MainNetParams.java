/*
 * Copyright 2013 Google Inc.
 * Copyright 2015 Andreas Schildbach
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.bitcoinj.params;

import org.bitcoinj.core.*;
import org.bitcoinj.net.discovery.*;

import java.net.*;

import static com.google.common.base.Preconditions.*;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends AbstractBitcoinNetParams {
    public static final int MAINNET_MAJORITY_WINDOW = 1000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 950;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 750;

    public MainNetParams() {
        super();

        genesisBlock.setDifficultyTarget(0x1e0ffff0L);  // this is ok -- fh
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x1e0fffffL);
        addressHeader = 63;
        dumpedPrivateKeyHeader = 128 + addressHeader;
        p2shHeader = 5;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 8335;
        packetMagic = 0x534f4e47;
        bip32HeaderPub = 0x0488B21E; //The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderPriv = 0x0488ADE4; //The 4 byte header that serializes in base58 to "xprv"

        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        genesisBlock.setDifficultyTarget(0x1e0ffff0L);
        genesisBlock.setTime(1409546382L); // 1413817324  was 1317972665L -- fh
        genesisBlock.setNonce(2360922209L); // 1591189  was 2084524493L
        id = ID_MAINNET;
        subsidyDecreaseBlockCount = 1051200;  // lets check this - what do we do with this? 
        spendableCoinbaseDepth = 120; // same with this..
        String genesisHash = "c2e1053b8b32c0aba8039421206c6760558d4fcd264394834fcf00a3245eca91";
    System.out.println("Checking genesis hash in mainnetparams");
        checkState(genesisHash.equals("c2e1053b8b32c0aba8039421206c6760558d4fcd264394834fcf00a3245eca91"), genesisHash);

       dnsSeeds = new String[] {
                "seed.songcoin.org"
        };
        httpSeeds = null;/*new HttpDiscovery.Details[] {
                // Andreas Schildbach
                new HttpDiscovery.Details(
                        ECKey.fromPublicOnly(Utils.HEX.decode("0238746c59d46d5408bf8b1d0af5740fe1a6e1703fcb56b2953f0b965c740d256f")),
                        URI.create("http://httpseed.bitcoin.schildbach.de/peers")
                )
        };*/

        addrSeeds = new int[] {
            0xB908A65E, 0xBC284E1F, 0xC063C89B,
        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}
