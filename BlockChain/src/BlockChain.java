import java.util.ArrayList;
import com.google.gson.GsonBuilder;

public class BlockChain {

    private static ArrayList<Block> blockchain = new ArrayList<>();
    private static int difficulty = 5;

    public static void main(String[] args) {
        blockchain.add(new Block("First block", "0"));
        System.out.println("Mining first block...");
        blockchain.get(0).mineBlock(difficulty);

        blockchain.add(new Block("Second block", blockchain.get(blockchain.size()-1).getHash()));
        System.out.println("Mining second block...");
        blockchain.get(1).mineBlock(difficulty);

        blockchain.add(new Block("Third block", blockchain.get(blockchain.size()-1).getHash()));
        System.out.println("Mining third block...");
        blockchain.get(2).mineBlock(difficulty);

        System.out.println("\nBlockchain is Valid: " + isChainValid());

        String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
        System.out.println(blockchainJson);
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
