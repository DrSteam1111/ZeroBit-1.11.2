package com.drsteam1111.zerobit.handlers;

import net.minecraft.util.IStringSerializable;

/**
 * Created by edvin on 2017-06-01.
 */
public class EnumHandler {

    public static enum MachineTypes implements IStringSerializable {
        BASIC("basic", 0),
        ADVANCED("advanced", 1);

        private int ID;
        private String name;

        private MachineTypes(String name, int ID) {
            this.ID = ID;
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getID() {
            return ID;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public static enum MaterialTypes implements IStringSerializable {
        RUBBER("rubber", 0),
        RESSENCE("rubber_essence", 1);

        private int ID;
        private String name;

        private MaterialTypes(String name, int ID) {
            this.ID = ID;
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getID() {
            return ID;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public static enum IngotTypes implements IStringSerializable {
            COPPER("copper", 0),
            SILVER("silver", 1),
            TIN("tin", 2),
            COMPRESSED("compressed", 3);

            private int ID;
            private String name;

            IngotTypes(String name, int ID) {
                this.name = name;
                this.ID = ID;
            }

            @Override
            public String getName() {
                return this.name;
            }

            public int getID() {
                return ID;
            }

            @Override
            public String toString() {
                return getName();
            }
    }

    public static enum oreTypes implements IStringSerializable {
            COPPER("copper", 0),
            SILVER("silver", 1),
            TIN("tin", 2);

            private int ID;
            private String name;

            oreTypes(String name, int ID) {
                this.name = name;
                this.ID = ID;
            }

            @Override
            public String getName() {
                return this.name;
            }

            public int getID() {
                return ID;
            }

            @Override
            public String toString() {
                return getName();
            }
    }
}
