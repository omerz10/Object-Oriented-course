package sprites;

import animations.ColorsParser;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.LineNumberReader;
import java.io.IOException;
import java.lang.reflect.Field;

import java.util.Map;
import java.util.TreeMap;

/**
 * BlocksDefinitionReader.
 */
public class BlocksDefinitionReader {

    /**
     * Returns BlocksFromSymbolsFactory.
     *
     * @param reader file
     * @return BlocksFromSymbolsFactory
     */
    public static BlocksFromSymbolsFactory fromReader(java.io.Reader reader) {
        String[] blocksInStrings = null;
        BlocksFromSymbolsFactory blocksSymbolFac = new BlocksFromSymbolsFactory();
        BlocksDefinitionReader blocksD = new BlocksDefinitionReader();
        String blocksString, finalString = "";
        try {
            LineNumberReader lineReader = new LineNumberReader(reader);
            while ((blocksString = lineReader.readLine()) != null) {
                finalString += blocksString + "\n";
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        blocksInStrings = finalString.split("\n");
        blocksSymbolFac = blocksD.stringsToBlocksSymbols(blocksInStrings);
        return blocksSymbolFac;
    }

    /**
     * Turn list of string ti a BlockFromSymbolFactory.
     *
     * @param strings strings
     * @return BlockFromSymbolFactory
     */
    public BlocksFromSymbolsFactory stringsToBlocksSymbols(String[] strings) {
        // Create block's parameters
        int defaultHeight = -1, defaultWidth = -1, defaultHitPoints = -1;
        int height = -1, width = -1, hitPoints = -1;
        String symbol = "", fillF = "";
        Color defaultStroke = null, stroke = null, color = null;
        ColorsParser colorP = new ColorsParser();
        Map<String, String> defaultFill = new TreeMap<String, String>();
        Map<String, String> fill = new TreeMap<String, String>();
        BlocksFromSymbolsFactory blockSymbol = new BlocksFromSymbolsFactory();
        BufferedImage img = null;

        // run through default values for blocks
        for (String str : strings) {
            if (str.contains("default height")) {
                String[] splitedLine = str.split(" ");
                // Run through default values (line 2)
                for (String s : splitedLine) {
                    if (s.contains("height")) {
                        defaultHeight = Integer.parseInt(s.split(":")[1]);
                    } else if (s.contains("width")) {
                        defaultWidth = Integer.parseInt(s.split(":")[1]);
                    } else if (s.contains("hit_points")) {
                        defaultHitPoints = Integer.parseInt(s.split(":")[1]);
                    } else if (s.contains("stroke")) {
                        defaultStroke = colorP.colorFromString(s.split(":")[1]);
                    } else if (s.contains("fill")) {
                        defaultFill.put(s.split(":")[0], s.split(":")[1]);
                    }
                }
            }
            if (str.contains("bdef")) {
                if (!str.contains("fill")) {
                    fill.putAll(defaultFill);
                }
                if (!str.contains("height")) {
                    height = defaultHeight;
                }
                if (!str.contains("width")) {
                    width = defaultWidth;
                }
                if (!str.contains("hit_points")) {
                    hitPoints = defaultHitPoints;
                }
                if (!str.contains("stroke")) {
                    stroke = defaultStroke;
                }
                String[] splitedLine2 = str.split(" ");
                //Run through each line contains bdef
                for (String s2 : splitedLine2) {
                    if (s2.contains("symbol")) {
                        symbol = s2.split(":")[1];
                    }
                    if (s2.contains("height")) {
                        height = Integer.parseInt(s2.split(":")[1]);
                    }
                    if (s2.contains("width")) {
                        width = Integer.parseInt(s2.split(":")[1]);
                    }
                    if (s2.contains("hit_points")) {
                        hitPoints = Integer.parseInt(s2.split(":")[1]);
                    }
                    if (s2.contains("stroke")) {
                        stroke = colorP.colorFromString(s2.split(":")[1]);
                    }
                    if (s2.contains("fill")) {
                        fill.put(s2.split(":")[0], s2.split(":")[1]);
                    }
                }
                // create fill for block
                String fillValue = "fill-" + Integer.toString(hitPoints);
                if (fill.containsKey("fill-" + Integer.toString(hitPoints))) {
                    fillF = fill.get(fillValue);
                } else {
                    fillValue = "fill";
                    fillF = fill.get(fillValue);
                }
                if (fillF.contains("image")) {
                    img = null;
                    try {
                        String fillR = fillF.replace("image(", "").replace(")",
                                "");
                        img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(fillR));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                    color = null;
                } else {
                    if (fillF.contains("RGB")) {
                        String sp = fillF.replace("color(RGB(", "");
                        sp = sp.replace(")", "");
                        String[] numbers = sp.split(",");
                        color = new Color(
                                Integer.parseInt(numbers[0]),
                                Integer.parseInt(numbers[1]),
                                Integer.parseInt(numbers[2]));
                    } else {
                        try {
                            String st1 = "java.awt.Color";
                            Field field = Class.forName(st1).getField(fillF.replace("color(",
                                    "").replace(")", ""));
                            color = (Color) field.get(null);
                        } catch (Exception e) {
                            throw new IllegalArgumentException("Invalid color");
                        }
                    }
                    img = null;
                }
                Double[] sizes = new Double[2];
                sizes[0] = (double) width;
                sizes[1] = (double) height;
                BlockCreation blockCreate = new BlockCreation(sizes, color, hitPoints, stroke, fill, fillF, img);
                blockSymbol.addBlockCreator(symbol, blockCreate);
            } else if (str.contains("sdef")) {
                String newSymbol = "";
                int num = -1;
                String[] spacers = str.split(" ");
                for (String sp : spacers) {
                    if (sp.contains("symbol")) {
                        newSymbol = sp.split(":")[1];
                    }
                    if (sp.contains("width")) {
                        num = Integer.parseInt(sp.split(":")[1]);
                    }
                }
                blockSymbol.addSpacer(newSymbol, num);
            }
        }
        return blockSymbol;
    }
}

