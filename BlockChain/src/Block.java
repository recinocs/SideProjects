import java.util.Date;

public class Block {

    private String hash;
    private String previousHash;
    private String data;
    private long timeStamp;
    private int nonce;

    Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    String calculateHash() {
        return StringUtil.applySha256(previousHash +
                Long.toString(timeStamp) +
                Integer.toString(nonce) +
                data);
    }

    void mineBlock(int difficulty) {
        String target = new String(new char[difficulty]).replace('\0', '0');
        while(!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public String getHash() {
        return this.hash;
    }

    public String getPreviousHash() {
        return this.previousHash;
    }
}
