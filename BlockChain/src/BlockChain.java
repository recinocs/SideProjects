import java.security.Security;
import java.util.ArrayList;
import com.google.gson.GsonBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class BlockChain {

    private static ArrayList<Block> blockchain = new ArrayList<>();
    private static int difficulty = 5;
    public static Wallet walletA;
    public static Wallet walletB;

    public static void main(String[] args) {
        Security.addProvider(new BouncyCastleProvider());

        walletA = new Wallet();
        walletB = new Wallet();

        System.out.println("Private and public keys: ");
        System.out.println(StringUtil.getStringFromKey(walletA.getPrivateKey()));
        System.out.println(StringUtil.getStringFromKey(walletA.getPublicKey()));

        Transaction transaction = new Transaction(walletA.getPublicKey(), walletB.getPublicKey(), 5, null);
        transaction.generateSignature(walletA.getPrivateKey());

        System.out.println("Is signature verified");
        System.out.println(transaction.verifySignature());
    }

    private static Boolean isChainValid() {
        Block current;
        Block previous;
        String hashTarget = new String(new char[difficulty]).replace('\0', '0');

        if(blockchain.size() > 2) {
            for(int i = 1; i < blockchain.size(); i++) {
                current = blockchain.get(i);
                previous = blockchain.get(i-1);

                if(!current.getHash().equalsIgnoreCase(current.calculateHash())) {
                    System.out.println("Current hashes not equal");
                    return false;
                }

                if(!previous.getHash().equalsIgnoreCase(current.getPreviousHash())) {
                    System.out.println("Previous hashes not equal");
                    return false;
                }

                if(!current.getHash().substring(0, difficulty).equals(hashTarget)) {
                    System.out.println("This block hasn't been mined");
                    return false;
                }
            }
        }
        return true;
    }
}
