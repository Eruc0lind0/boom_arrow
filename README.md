# make_it_boom
A Minecraft mod for making things that go boom.

To build this, for the sake of my sanity, I shall list what I did here:

1) Delete everything in C:/Users/<user>/.gradle - let's start clean, particularly if things aren't working first time.
2) Unzip the project into a directory
3) Open it in Intellij (I may or may not have imported the gradle project at this point - it won't work though if I did)
4) From OUTSIDE Intellij (i.e. not in the Intellij terminal), run the following gradle commands from the project dir:
    - gradlew.bat genIntellijRuns
    - gradlew.bat build
5) Refresh the project in Intellij and hope for the best.
6) Once the above is done, the Intellij Gradle plugin _should_ recognise and allow us to run tasks. Then we know it's working.

Here I will also list what I've learnt. This'll be basic stuff, probably, but at least I won't forget.

### FIRST THING'S FIRST
Update the meta data files (mod.toml / build.gradle / pack.mcmeta) so that they're correct for the new mod. In particular:
- mod.toml has the details of the mod that will display in the Mod menu within Minecraft
- pack.mcmeta probably doesn't need changing, though the description should probably include the mod name
- build.gradle is where we set the version, the group id and the archive name, i.e. the mod name. It also describes the manifest.

### CREATE NEW ITEM

First, we need a class for registering items (i.e. ModItems). In this class, we need to:
- Set up a DeferredRegister, a sort of list for holding items.
- Do item registrations to this DeferredRegister. Below, I'm creating a new generic item, adding it to the Misc tab and enabling stacks of 64.
- Create a register method that registers all the items added to the DeferredRegister.

```java
   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MakeItBoom.MODID);

    public static final RegistryObject<Item> make_it_boom = ITEMS.register("make_it_boom",
            () -> new Item(new Item.Properties()
                    .tab(CreativeModeTab.TAB_MISC)
                    .stacksTo(64)));
                    
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }                    
```

In our main class, we need to:
- Get the IEventBus from Forge
- Register all the items in ModItems
- Register this to the Forge EVENT_BUS

```java
     IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
     ModItems.register(modEventBus);
     MinecraftForge.EVENT_BUS.register(this);
```

That's essentially all the code required to put a new item (that doesn't really do anything) into Minecraft. We also need the following resources:

- resources/assets/<mod_name>/
  - lang/en_us.json: a json file that defines the name of the item (in US English)
  ```json
  {
    "item.<mod_name>.<item_name>": "Item Name In Game"
  }
  ```
  - models/item/<item_name>.json: a json file that defines what model to use and, in the layer0 key, the name of the texture file to use
  ```json
    {
    "parent": "item/generated",
    "textures": {
      "layer0": "<mod_name>:item/<item_name>"
      }
    }
  ```
  - textures/item/<item_name>.png: the PNG texture file

### ADD A RECIPE

Recipes are fairly simple - just JSON files under:
- data/<mod_name>/recipes

> **You can look at Vanilla Minecraft recipes in the following package**:
> - net.minecraft:client:extras:ver
 
### ADD A TAB (IF YOU WANT)

You can add a tab fairly easily - create a new class that defines a bunch of static finals that essentially create new CreativeModTab instances.
Then, when registering the item, we can use this tab when setting the ItemProperties.

```java
    public static final CreativeModeTab make_it_boom_TAB = new CreativeModeTab("makeitboomtab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.make_it_boom.get());
        }
    };
```

### ARROWS ARE SPECIAL

It turns out that arrows are special. Whilst it's simple enough to extend the ArrowItem and Arrow classes and make something that is
essentially the same as an existing type of arrow, the important thing seems to be that we need to create a JSON override (or addition)
to the existing file(s) in these locations:
- resources/data/minecraft/tags/entity_types/arrows.json
- resources/data/minecraft/tags/items/arrows.json

We can copy the general structure of the files from net.minecraft:client:extras:ver - but, the first key in the json should be replace=false, i.e.

```json
{
  "replace": false,
  "values": [
    "makeitboom:make_it_boom"
  ]
}
```

This then lets Minecraft know that the item you've created is a type of arrow, so can be fired from a bow.

In short, we need:
- A class that extends ArrowItem
  - This will then need to override the createArrow method at the minimum, but probably isInfinite as well
- A class that extends AbstractArrow
  - This will need to override getPickupItem, so we pick up the right type of item
- If we want specific rendering, then a class that extends ArrowRenderer
  - God knows how this actually works - one thing worth mentioning is that we need a specific texture to render an arrow that has been fired. These are present in:
    - resources/assets/makeitboom/textures/entity/projectiles

### MAKE THEM EXPLODE

There are probably several ways to do this - the one I found that worked was to use the Level.explode() method.
In the entity, we can override other AbstractArrow methods to determine how to explode a thing. I chose to explode when the arrow hits a block. Therefore:

```java
    @Override
    protected void onHitBlock(BlockHitResult blockHitResult) {
        super.onHitBlock(blockHitResult);
        BlockPos blockPos = blockHitResult.getBlockPos();
        level.explode(this,
                blockPos.getX(),
                blockPos.getY(),
                blockPos.getZ(),
                strength,
                true,
                Explosion.BlockInteraction.DESTROY
        );
        this.discard();
    }
```

Points to remember:
- Call this.discard() otherwise the arrow never dies and keeps exploding forever. This is a problem.
- You can hardcode 'strength' to a float here. Instead, I chose to pass it into the Constructor of MakeItBoomEntity, in case I wanted different strength arrows, i.e. different types.
- I set the base damage of the arrow using arrow.setBaseDamage() when creating an arrow. I set this high, because I wanted the arrows to kill things easily - but not explode, crucially.