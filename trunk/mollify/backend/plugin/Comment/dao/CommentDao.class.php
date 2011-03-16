<?php

	/**
	 * Copyright (c) 2008- Samuli J�rvel�
	 *
	 * All rights reserved. This program and the accompanying materials
	 * are made available under the terms of the Eclipse Public License v1.0
	 * which accompanies this distribution, and is available at
	 * http://www.eclipse.org/legal/epl-v10.html. If redistributing this code,
	 * this entire header must remain intact.
	 */
	
	class CommentDao {
		private $env;

		public function __construct($env) {
			$this->env = $env;
		}
		
		public function getCommentCount($item) {
			$db = $this->env->configuration()->db();
			return $db->query("select count(`id`) from ".$db->table("comment")." where `item_id` = ".$db->string($item->id(), TRUE))->value(0);
		}

		public function getComments($item) {
			$db = $this->env->configuration()->db();
			return $db->query("select u.id as user_id, u.name as username, c.time, c.comment from ".$db->table("comment")." c, ".$db->table("user")." u where c.`item_id` = ".$db->string($item->id(), TRUE)." and u.id = c.user_id order by time desc")->rows();
		}
		
		public function addComment($userId, $item, $time, $comment) {
			$db = $this->env->configuration()->db();
			$db->update(sprintf("INSERT INTO ".$db->table("comment")." (user_id, item_id, time, comment) VALUES (%s, %s, %s, %s)", $db->string($userId, TRUE), $db->string($item->id(), TRUE), $db->string(date('YmdHis', $time)), $db->string($comment, TRUE)));
			return $db->lastId();
		}
		
		public function deleteComments($item) {
			$db = $this->env->configuration()->db();
			if ($item->isFile())
				return $db->update("DELETE FROM ".$db->table("comment")." WHERE `item_id` = ".$db->string($item->id(), TRUE));
			else
				return $db->update(sprintf("DELETE FROM ".$db->table("comment")." WHERE `item_id` like '%s%%'", $db->string($item->id())));
		}

		public function moveComments($item, $to) {
			$db = $this->env->configuration()->db();
			if ($item->isFile())
				return $db->update("UPDATE ".$db->table("comment")." SET `item_id` = ".$db->string($to->id(), TRUE) ." where `item_id` = ".$db->string($item->id(), TRUE));
			else
				return $db->update(sprintf("UPDATE ".$db->table("comment")." SET item_id=CONCAT('%s', SUBSTR(item_id, %d)) WHERE item_id like '%s%%'", $to->id(), strlen($item->id())+1, $item->id()));
		}
						
		public function __toString() {
			return "CommentDao";
		}
	}
?>