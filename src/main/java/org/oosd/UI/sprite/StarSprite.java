    package org.oosd.UI.sprite;

    import javafx.scene.Node;
    import org.oosd.model.GameEntity;

    public class StarSprite implements Sprite<GameEntity, Node> {
        private final Node star;
        private final GameEntity entity;

        public StarSprite(GameEntity entity) {
            this.entity = entity;
            star = new Star();
            update();
        }

        @Override
        public Node getNode() {
            return star;
        }

        @Override
        public GameEntity getEntity() {
            return entity;
        }

        @Override
        public void update() {
            star.setTranslateX(entity.getX());
            star.setTranslateY(entity.getY());
        }
    }
