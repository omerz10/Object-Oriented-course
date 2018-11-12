package levels;

import animations.ColorsParser;
import collisions.Velocity;
import geometrics.Point;
import sprites.BackgroundSprite;
import sprites.Block;
import sprites.BlocksDefinitionReader;
import sprites.BlocksFromSymbolsFactory;

import javax.imageio.ImageIO;
import java.awt.Color;


import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * LevelSpecificationReader.
 */
public class LevelSpecificationReader {

    /**
     * Returns list of level (LevelInformation).
     *
     * @param reader given reader
     * @return list of level (LevelInformation)
     */
    public List<LevelInformation> fromReader(java.io.Reader reader) {
        ArrayList<LevelInformation> levelInfoList = new ArrayList<LevelInformation>();
        ArrayList<String> stringsList = new ArrayList<String>();
        String line, level = "";
        BufferedReader br = null;
        try {
            br = new BufferedReader(reader);
            // run through all level
            while ((line = br.readLine()) != null) {
                // run through specific level
                while ((line = br.readLine()) != null) {
                    if (line.equals("END_LEVEL")) {
                        break;
                    }
                    if (!line.equals("START_LEVEL")) {
                        level += line + "\n";
                    }
                }
                stringsList.add(level);
                // clear level
                level = "";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        // run through each level
        for (String stringLevel : stringsList) {
            try {
                LevelInformation levelInfo = createLevelInfo(stringLevel);
                levelInfoList.add(levelInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return levelInfoList;
    }

    /**
     * Retruns level (LevelInformation).
     *
     * @param strings all srings of one level
     * @return level (LevelInformation)
     * @throws Exception e
     */
    LevelInformation createLevelInfo(String strings) throws Exception {
        if ((!strings.contains("level_name")) || (!strings.contains("ball_velocities"))
                || (!strings.contains("background")) || (!strings.contains("paddle_speed"))
                || (!strings.contains("paddle_width")) || (!strings.contains("block_definitions"))
                || (!strings.contains("blocks_start_x")) || (!strings.contains("blocks_start_y"))
                || (!strings.contains("row_height")) || (!strings.contains("num_blocks"))
                || (!strings.contains("START_BLOCKS")) || (!strings.contains("END_BLOCKS"))) {
            throw new Exception("Does not contains of parameters");
            // strings has all parameters
        } else {
            String[] levelAndBlocks = strings.split("START_BLOCKS");
            String[] startLevel = levelAndBlocks[0].split("\n");
            String[] startBlocks = levelAndBlocks[1].split("\n");

            // Initialize level's parameters
            String levelName = "", background = "", blockDef = "";
            int paddleWidth = -1, blockStartX = -1, blockStartY = -1, rowHeight = -1, numBlocks = -1, paddleSpeed = 0;
            BackgroundSprite backgroundS = null;
            ArrayList<Velocity> ballVel = new ArrayList<Velocity>();
            for (String str : startLevel) {
                if (str.startsWith("level_name")) {
                    levelName = str.split(":")[1];
                } else if (str.startsWith("paddle_width")) {
                    paddleWidth = Integer.parseInt(str.split(":")[1]);
                } else if (str.startsWith("paddle_speed")) {
                    paddleSpeed = Integer.parseInt(str.split(":")[1]);
                } else if (str.startsWith("blocks_start_x")) {
                    blockStartX = Integer.parseInt(str.split(":")[1]);
                } else if (str.startsWith("blocks_start_y")) {
                    blockStartY = Integer.parseInt(str.split(":")[1]);
                } else if (str.startsWith("row_height")) {
                    rowHeight = Integer.parseInt(str.split(":")[1]);
                } else if (str.startsWith("num_blocks")) {
                    numBlocks = Integer.parseInt(str.split(":")[1]);
                } else if (str.startsWith("block_definitions")) {
                    blockDef = str.split(":")[1];
                } else if (str.startsWith("ball_velocities")) {
                    String[] velocities = str.split(":")[1].split(" ");
                    for (String string : velocities) {
                        ballVel.add(Velocity.fromAngleAndSpeed(Integer.parseInt(string.split(",")[0]),
                                Integer.parseInt(string.split(",")[1])));
                    }
                } else if (str.startsWith("background")) {
                    Color color = null;
                    background = str.split(":")[1];
                    // Import image from file
                    BufferedImage image = null;

                    if (background.contains("image")) {
                        try {
                            background = background.replace("image(", "").replace(")",
                                    "");
                            InputStream img = ClassLoader.getSystemClassLoader().getResourceAsStream(background);
                            image = ImageIO.read(img);
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        } finally {
                            backgroundS = new BackgroundSprite(new Point(0, 0), 800, 600, image);
                        }
                        // Get color from file
                    } else {
                        ColorsParser cp = new ColorsParser();
                        color = cp.colorFromString(str.split(":")[1]);
                        backgroundS = new BackgroundSprite(new Point(0, 0), 800, 600, color);
                    }
                }
            }
            // Create Blocks Symbol Factory
            InputStream inputS = ClassLoader.getSystemClassLoader().getResourceAsStream(blockDef);
            java.io.Reader reader = new InputStreamReader(inputS);
            BlocksFromSymbolsFactory blocksSF = new BlocksFromSymbolsFactory();
            blocksSF = BlocksDefinitionReader.fromReader(reader);

            // Run through blocks symbols
            double x = blockStartX, y = blockStartY;
            ArrayList<Block> spritesBlocks = new ArrayList<Block>();
            for (String bSymbol : startBlocks) {
                for (int j = 0; j < bSymbol.length(); j++) {
                    if (blocksSF.isSpaceSymbol(Character.toString(bSymbol.charAt(j)))) {
                        x += blocksSF.getSpaceWidth(Character.toString(bSymbol.charAt(j)));
                    } else if (blocksSF.isBlockSymbol(Character.toString(bSymbol.charAt(j)))) {
                        Block b = blocksSF.getBlock(Character.toString(bSymbol.charAt(j)), (int) x, (int) y);
                        spritesBlocks.add(b);
                        x += b.getWidth();
                    }
                }
                if (!bSymbol.equals("")) {
                    y += rowHeight;
                    x = blockStartX;
                }
            }
            // Create level
            LevelInformation newLevel = new LevelCreator(levelName, backgroundS, ballVel, paddleSpeed, paddleWidth,
                    spritesBlocks);
            return newLevel;
        }
    }

    /**
     * Returns a reader for blocks.
     *
     * @param definitions a string
     * @return a reader
     * @throws Exception Exception
     */
    public java.io.Reader createBlocksReader(String definitions) throws Exception {
        InputStream is = null;
        String blocksString, finalString = "";
        try {
            is = ClassLoader.getSystemClassLoader().getResourceAsStream(definitions);
            LineNumberReader lineReader = new LineNumberReader(new InputStreamReader(is));
            while ((blocksString = lineReader.readLine()) != null) {
                finalString += blocksString + "\n";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        InputStream inputS = ClassLoader.getSystemClassLoader().getResourceAsStream(finalString);
        java.io.Reader reader = new InputStreamReader(inputS);
        return reader;
    }


}
